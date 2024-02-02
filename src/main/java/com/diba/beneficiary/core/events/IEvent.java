package com.diba.beneficiary.core.events;

import java.util.UUID;

public interface IEvent {
    String correlationId = UUID.randomUUID().toString();
}
