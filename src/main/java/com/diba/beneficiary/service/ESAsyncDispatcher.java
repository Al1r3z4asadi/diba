package com.diba.beneficiary.service;

import com.diba.beneficiary.core.command.ICommand;
import com.diba.beneficiary.core.command.ICommandDispatcher;
import com.diba.beneficiary.core.utils.UserMetadata;
import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ESAsyncDispatcher implements ICommandDispatcher<WriteResult> {
    private final EventStoreDBClient eventStoreDBClient;

    @Autowired
    public ESAsyncDispatcher(EventStoreDBClient eventStoreDBClient) {
        this.eventStoreDBClient = eventStoreDBClient;
    }
    @Override
    public <T extends ICommand> CompletableFuture<WriteResult> dispatch(T command) {
        String commandStreamName = getCommandStreamName(command);
        EventData eventData = createEventData(command);
        return eventStoreDBClient.appendToStream(commandStreamName, eventData);
    }

    private <C extends ICommand> String getCommandStreamName(C command) {
        return "command-";
    }

    private <C extends ICommand> EventData createEventData(C command) {
        String correlationId = UUID.randomUUID().toString();
        //can add more metadata
        UserMetadata userMetadata = new UserMetadata(
                UUID.randomUUID().toString(),
                null
        );
        return EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        command.getClass().toString(),
                        command
                ).metadataAsJson(userMetadata)
                .build();
    }
}
