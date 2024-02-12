package com.diba.beneficiary.infrastructure.mongo;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseModel {
    private UUID id;
    private long updatedAt;

    public BaseModel() {
        id = UUID.randomUUID();
        updatedAt = Instant.now().getEpochSecond();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}

