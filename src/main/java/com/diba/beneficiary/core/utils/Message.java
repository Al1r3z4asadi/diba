package com.diba.beneficiary.core.utils;

import java.util.UUID;

public interface Message {
    String getCorrelationId();
    String getCausationId();
}
