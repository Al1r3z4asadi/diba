package com.diba.beneficiary.core.utils;

import com.diba.beneficiary.core.events.EventMetadata;
import com.eventstore.dbclient.ResolvedEvent;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

import java.util.Optional;

public record MessageEnvelope<Event extends Message>(
        Event data,
        EventMetadata metadata
) implements ResolvableTypeProvider {

    public static <Event extends Message> Optional<MessageEnvelope<Event>> of(final Class<Event> type
                                , ResolvedEvent resolvedEvent) {
        if (type == null)
            return Optional.empty();

        var eventData = MessageSerializer.deserialize(type, resolvedEvent);
        if (eventData.isEmpty())
            return Optional.empty();

        return Optional.of(
                new MessageEnvelope<>(
                        eventData.get(),
                        new EventMetadata(
                                "s" , "d" ,
                                resolvedEvent.getEvent().getEventId().toString()
                                , 3  ,2 , null
//                                resolvedEvent.getEvent().getRevision(),
//                                resolvedEvent.getEvent().getPosition().getCommitUnsigned(),
//                                resolvedEvent.getEvent().getEventType()
                        )
                )
        );
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                getClass(), ResolvableType.forInstance(data)
        );
    }
}