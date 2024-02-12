package com.diba.beneficiary.core.service.eventbus;

import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;
import com.diba.beneficiary.shared.messages.events.IEvent;

public interface IEventBus {

    <event extends IEvent> void publish(MessageEnvelope<event> event);
}

