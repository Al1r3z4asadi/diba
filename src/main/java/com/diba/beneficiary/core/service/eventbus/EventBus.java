package com.diba.beneficiary.core.service.eventbus;

import com.diba.beneficiary.shared.messages.events.IEvent;
import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;

public class EventBus implements IEventBus {

    @Override
    public <event extends IEvent> void publish(MessageEnvelope<event> event) {

    }
}
