package com.diba.beneficiary.report;

import com.diba.beneficiary.shared.messages.events.EventMetadata;

public interface VersionedView {
    long getLastProcessedPosition();

    void setMetadata(EventMetadata eventMetadata);
}

