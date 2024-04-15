package com.diba.beneficiary.core.service.eventbus;

import com.diba.beneficiary.shared.messages.utils.Message;
import com.diba.beneficiary.shared.messages.utils.MessageEnvelope;

public interface IEventBus {

    <event extends Message> void publish(MessageEnvelope<event> event);
}

