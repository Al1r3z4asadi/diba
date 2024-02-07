package com.diba.beneficiary.core.messages.events.eventbus;

import com.diba.beneficiary.core.messages.utils.MessageEnvelope;

public class EventBus implements IEventBus {

    @Override
    public <event extends IEvent> void publish(MessageEnvelope<event> event) {

    }
}
