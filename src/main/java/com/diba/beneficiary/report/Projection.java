package com.diba.beneficiary.report;

import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
import com.diba.beneficiary.shared.messages.events.IEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.function.Supplier;

public abstract class Projection<View, Id> {
    private final ReactiveMongoRepository<View, Id> repository;
    private final Logger logger = LoggerFactory.getLogger(Projection.class);

    protected <Event extends IEvent> void add(MessageEnvelope<Event> eventEnvelope, Supplier<View> handle) {
        var result = handle.get();
        repository.save(result);
    }

    protected Projection(ReactiveMongoRepository<View, Id> repository) {
        this.repository = repository;
    }



}
