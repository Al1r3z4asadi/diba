package com.diba.beneficiary.core.command.listener;

import com.diba.beneficiary.core.command.ICommandBus;
import com.diba.beneficiary.core.utils.Message;
import com.diba.beneficiary.core.utils.MessageEnvelope;
import com.diba.beneficiary.core.utils.MessageTypeMapper;
import com.eventstore.dbclient.ResolvedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

public record CommandListener(
        ApplicationEventPublisher applicationEventPublisher
) implements ICommandBus {
    @Override
    public <command extends Message> void publish(MessageEnvelope<command> command) {
        applicationEventPublisher.publishEvent(command);
    }

    public void handleEvent(ResolvedEvent resolvedEvent) {
        if (isEventWithEmptyData(resolvedEvent))
            return;
        var eventClass = MessageTypeMapper.toClass(resolvedEvent.getEvent().getEventType());
        var streamEvent = eventClass.flatMap(c -> MessageEnvelope.of(c, resolvedEvent));
        if (streamEvent.isEmpty()) {
            return;
        }
        this.publish((MessageEnvelope<?>) streamEvent.get());
    }

    private boolean isEventWithEmptyData(ResolvedEvent resolvedEvent) {
        if (resolvedEvent.getEvent().getEventData().length != 0) return false;
            return true;
    }




}
