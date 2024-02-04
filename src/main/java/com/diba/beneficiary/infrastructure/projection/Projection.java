package com.diba.beneficiary.infrastructure.projection;

import com.diba.beneficiary.core.utils.MessageEnvelope;
import com.diba.beneficiary.core.events.IEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.function.Supplier;

public abstract class Projection<View, Id> {
    private final MongoRepository<View, Id> repository;
    private final Logger logger = LoggerFactory.getLogger(Projection.class);

    protected <Event extends IEvent> void add(MessageEnvelope<Event> eventEnvelope, Supplier<View> handle) {
        var result = handle.get();
        repository.save(result);
    }

    protected Projection(MongoRepository<View, Id> repository) {
        this.repository = repository;
    }

}
