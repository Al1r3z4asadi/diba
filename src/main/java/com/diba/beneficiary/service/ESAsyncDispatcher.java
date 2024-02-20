package com.diba.beneficiary.service;

import com.diba.beneficiary.core.service.Ihandlers.ICommandHandler;
import com.diba.beneficiary.shared.messages.command.Command;
import com.diba.beneficiary.core.service.IMessageDispatcher;
import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.ServiceResult;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;
import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Scope("singleton")
public class ESAsyncDispatcher<R> implements IMessageDispatcher<R> {
    private final EventStoreDBClient eventStoreDBClient;
    private final ICommandHandler _chandler ;
//    private final IQueryHandler _qhandler;

    @Autowired
    public ESAsyncDispatcher(EventStoreDBClient eventStoreDBClient , ICommandHandler<Command> commandHandler) {
        this.eventStoreDBClient = eventStoreDBClient;
        this._chandler = commandHandler ;
//        this._qhandler = queryHandler ;
    }
    @Override
    public <T extends Message> CompletableFuture<ServiceResult<R>> dispatch(T message) {
        String commandStreamName = getCommandStreamName(message);
        EventData eventData = createEventData(message);

        CompletableFuture.supplyAsync(()-> eventStoreDBClient.appendToStream(commandStreamName, eventData)).join();
        if(message instanceof Command) {
            Command c = (Command)  message ;
            c.setId(eventData.getEventId());
            return this._chandler.handle(c);
        }
            else
//            if (message instanceof IQUERY)
            return new CompletableFuture<>();
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
        return EventData
                .builderAsJson(
                        eventId,
                        message.getClass().toString(),
                        message
                ).metadataAsJson(userMetadata)
                .build();
    }
}
