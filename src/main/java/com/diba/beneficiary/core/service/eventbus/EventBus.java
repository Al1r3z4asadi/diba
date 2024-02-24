package com.diba.beneficiary.core.service.eventbus;

import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
import org.springframework.context.ApplicationEventPublisher;

public record EventBus (
        ApplicationEventPublisher applicationEventPublisher
) implements IEventBus {

    @Override
    public <EVENT extends Message> void publish(MessageEnvelope<EVENT> event) {
        applicationEventPublisher.publishEvent(event);
    }
}
