package com.diba.beneficiary.service;

import com.diba.beneficiary.core.messages.ICommandHandler;
import com.diba.beneficiary.core.messages.command.ICommand;
import com.diba.beneficiary.core.messages.IMessageDispatcher;
import com.diba.beneficiary.core.messages.utils.Message;
import com.diba.beneficiary.core.utils.ServiceResult;
import com.diba.beneficiary.core.messages.utils.UserMetadata;
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
    public ESAsyncDispatcher(EventStoreDBClient eventStoreDBClient , ICommandHandler<ICommand> commandHandler) {
        this.eventStoreDBClient = eventStoreDBClient;
        this._chandler = commandHandler ;
//        this._qhandler = queryHandler ;
    }
    @Override
    public <T extends Message> CompletableFuture<ServiceResult<R>> dispatch(T message) {
        String commandStreamName = getCommandStreamName(message);
        EventData eventData = createEventData(message);
        var result = CompletableFuture.runAsync(()-> eventStoreDBClient.appendToStream(commandStreamName, eventData));
//        return  ;
        if(message instanceof ICommand)
            return result.thenCompose(f  -> this._chandler.handle((ICommand) message));
        else
//            if (message instanceof IQUERY)
            return new CompletableFuture<>();
    }

    private <C extends Message> String getCommandStreamName(C message) {
        return "command-";
    }

    private <C extends Message> EventData createEventData(C message) {
        String correlationId = UUID.randomUUID().toString();
        UserMetadata userMetadata = new UserMetadata(
                UUID.randomUUID().toString(),
                null
        );
        return EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        message.getClass().toString(),
                        message
                ).metadataAsJson(userMetadata)
                .build();
    }
}
