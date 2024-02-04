package com.diba.beneficiary.core.command;

import com.diba.beneficiary.core.utils.MessageEnvelope;
import com.diba.beneficiary.core.utils.Message;
import com.eventstore.dbclient.ResolvedEvent;

public interface ICommandBus {
    <command extends Message> void publish(MessageEnvelope<command> event);
    void handleEvent(ResolvedEvent resolvedEvent);
}
