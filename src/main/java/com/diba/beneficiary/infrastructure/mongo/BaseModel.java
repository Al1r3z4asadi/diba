package com.diba.beneficiary.infrastructure.mongo;

import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseModel {

    @Id
    private String id;

    private long updatedAt;

    public BaseModel() {
        id = UUID.randomUUID().toString();
        updatedAt = Instant.now().getEpochSecond();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}

