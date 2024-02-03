package com.diba.beneficiary.service;

import com.diba.beneficiary.core.command.ICommand;
import com.diba.beneficiary.core.command.ICommandDispatcher;
import com.diba.beneficiary.core.utils.ServiceResult;
import com.eventstore.dbclient.EventData;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ESAsyncDispatcher implements ICommandDispatcher {
    private final EventStoreDBClient eventStoreDBClient;

    @Autowired
    public ESAsyncDispatcher(EventStoreDBClient eventStoreDBClient) {
        this.eventStoreDBClient = eventStoreDBClient;
    }
    @Override
    public <T extends ICommand> ServiceResult dispatch(T command) {
        String commandStreamName = getCommandStreamName(command);
        EventData eventData = createEventData(command);

        eventStoreDBClient.appendToStream(commandStreamName, eventData)
                .join();
        return ServiceResult.success(null);
    }
    private <C extends ICommand> String getCommandStreamName(C command) {
        return "command-" + command.getClass().getSimpleName().toLowerCase();
    }

    private <C extends ICommand> EventData createEventData(C command) {
        String correlationId = UUID.randomUUID().toString();
        //can add more metadata
        return EventData
                .builderAsJson(
                        UUID.randomUUID(),
                        command.getClass().toString(),
                        command
                ).metadataAsJson(correlationId)
                .build();
    }
}
