package com.diba.beneficiary.service;

import com.diba.beneficiary.core.service.Ihandlers.ICommandHandler;
import com.diba.beneficiary.core.service.Ihandlers.IQueryHandler;
import com.diba.beneficiary.shared.messages.command.Command;
import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.shared.messages.command.Query;
import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;
import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Scope("singleton")
public class ESAsyncDispatcher<R> implements IMessageDispatcher<Object> {
    private final EventStoreDBClient eventStoreDBClient;
    private final ICommandHandler _chandler;
    private final IQueryHandler _qhandler;
    private final ObjectMapper _mapper;

    @Autowired
    public ESAsyncDispatcher(EventStoreDBClient eventStoreDBClient, ICommandHandler<Command> commandHandler,
                             IQueryHandler queryHandler, ObjectMapper mapper) {
        this.eventStoreDBClient = eventStoreDBClient;
        this._chandler = commandHandler;
        this._qhandler = queryHandler;
        _mapper = mapper;
    }

    @Override
    public <T extends Message> Mono<ServiceResult<Object>> dispatch(T message) {
        String commandStreamName = getCommandStreamName(message);
        EventData eventData = createEventData(message);

        CompletableFuture.supplyAsync(() -> eventStoreDBClient.appendToStream(commandStreamName, eventData));
        if (message instanceof Command) {
            Command c = (Command) message;
            c.setId(eventData.getEventId());
            return Mono.fromFuture(this._chandler.handle(c));
        }
        Query q = (Query) message;
        q.setId(eventData.getEventId());
        return this._qhandler.handle(q).collectList();
    }

    private <C extends Message> String getCommandStreamName(C message) {
        return "command-";
    }

    private <C extends Message> EventData createEventData(C message) {
        UserMetadata userMetadata = new UserMetadata(
                "",
                ""
        );
        UUID eventId = UUID.randomUUID();
        try {
            return EventData
                    .builderAsJson(
                            eventId,
                            message.getClass().toString(),
                            _mapper.writeValueAsBytes(message)
                    ).metadataAsJson(userMetadata)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
