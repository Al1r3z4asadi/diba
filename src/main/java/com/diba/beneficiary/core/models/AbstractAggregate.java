package com.diba.beneficiary.core.models;


import com.diba.beneficiary.core.exception.BeneficiaryException;
import com.diba.beneficiary.shared.messages.events.IEvent;
import com.diba.beneficiary.shared.messages.utils.UserMetadata;
import org.apache.catalina.User;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public abstract class AbstractAggregate<EVENT extends  IEvent, Id> implements Aggregate<Id> {
    protected Id id;
    protected int version;

    private final Queue uncommittedEvents = new LinkedList<>();
    public Id id() {
        return id;
    }
    public int version() {
        return version;
    }
    public Object[] dequeueUncommittedEvents() {
        var dequeuedEvents = uncommittedEvents.toArray();

        uncommittedEvents.clear();

        return dequeuedEvents;
    }

    public abstract void when(EVENT event) throws  Exception;
    protected void enqueue(EVENT event ) throws Exception {

        uncommittedEvents.add(event);
        when(event);
    }

}