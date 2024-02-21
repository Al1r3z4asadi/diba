package com.diba.beneficiary.core.service.eventbus;

import com.diba.beneficiary.shared.messages.events.IEvent;
import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
import org.springframework.context.ApplicationEventPublisher;

public record EventBus (
        ApplicationEventPublisher applicationEventPublisher
) implements IEventBus {

    @Override
    public <event extends IEvent> void publish(MessageEnvelope<event> event) {
        applicationEventPublisher.publishEvent(event);
    }
}
