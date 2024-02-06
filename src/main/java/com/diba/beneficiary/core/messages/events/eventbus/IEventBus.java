package com.diba.beneficiary.core.messages.events.eventbus;

import com.diba.beneficiary.core.utils.Message;
import com.diba.beneficiary.core.utils.MessageEnvelope;

public interface IEventBus {

    <event extends IEvent> void publish(MessageEnvelope<event> event);
}

