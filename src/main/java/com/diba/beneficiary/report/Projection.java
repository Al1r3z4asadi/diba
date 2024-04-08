package com.diba.beneficiary.report;

import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
import com.diba.beneficiary.shared.messages.events.IEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Projection<View, Id> {
    private final ReactiveMongoRepository<View, Id> repository;
    private final Logger logger = LoggerFactory.getLogger(Projection.class);

    protected Projection(ReactiveMongoRepository<View, Id> repository) {
        this.repository = repository;
    }

    protected <Event extends IEvent> void add(MessageEnvelope<Event> eventEnvelope, Supplier<View> handle) {
        var result = handle.get();
        if(result instanceof VersionedView versionedView){
            versionedView.setMetadata(eventEnvelope.metadata());
        }
        repository.save(result).block();
    }
    protected <Event extends IEvent> void getAndUpdate(Id viewId,
            MessageEnvelope<Event> eventEnvelope,
            Function<View, View> handle
    ) {
        View view = repository.findById(viewId).block();
        if (view == null) {
            logger.warn("View with id %s was not found for event %s".formatted(viewId, eventEnvelope.metadata().eventType()));
            return;
        }
        if (view instanceof VersionedView versionedView && wasAlreadyApplied(versionedView, eventEnvelope)) {
            logger.warn("View with id %s was already applied for event %s".formatted(viewId, eventEnvelope.metadata().eventType()));
            return;
        }
        var result = handle.apply(view);
        if(result instanceof VersionedView versionedView){
            versionedView.setMetadata(eventEnvelope.metadata());
        }
        repository.save(result).subscribe();
    }


    private static boolean wasAlreadyApplied(VersionedView view, MessageEnvelope<?> eventEnvelope) {
        var lastPosition = view.getLastProcessedPosition();
        var logPosition = eventEnvelope.metadata().streamPosition();
        return lastPosition >= logPosition;
    }
}
