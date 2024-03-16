package com.diba.beneficiary.command;

import com.diba.beneficiary.utils.Message;

import java.util.UUID;

public abstract class Command implements Message {
    private UUID id ;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id){
        this.id = id ;
    }
}
