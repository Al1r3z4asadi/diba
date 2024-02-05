package com.diba.beneficiary.core.models;


import com.diba.beneficiary.core.events.eventbus.IEvent;

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

    public abstract void when(EVENT event);

    protected UUID enqueue(EVENT event) {
        uncommittedEvents.add(event);
        when(event);
        version++;
        return UUID.randomUUID();
    }

}