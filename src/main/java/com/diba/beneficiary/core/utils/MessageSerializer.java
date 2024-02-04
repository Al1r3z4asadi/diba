package com.diba.beneficiary.core.utils;

import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventDataBuilder;
import com.eventstore.dbclient.ResolvedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class MessageSerializer {
    public static final ObjectMapper mapper =
            new JsonMapper()
                    .registerModule(new JavaTimeModule())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public static EventData serialize(Object event) {
        try {
            return EventDataBuilder.json(
                    UUID.randomUUID(),
                    MessageTypeMapper.toName(event.getClass()),
                    mapper.writeValueAsBytes(event)
            ).build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <Event> Optional<Event> deserialize(ResolvedEvent resolvedEvent) {
        var eventClass = MessageTypeMapper.toClass(resolvedEvent.getEvent().getEventType());

        if (eventClass.isEmpty())
            return Optional.empty();

        return deserialize(eventClass.get(), resolvedEvent);
    }

    public static <Event> Optional<Event> deserialize(Class<Event> eventClass, ResolvedEvent resolvedEvent) {
        try {

            var result = mapper.readValue(resolvedEvent.getEvent().getEventData(), eventClass);

            if (result == null)
                return Optional.empty();

            return Optional.of(result);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
