package com.diba.beneficiary.core.models;

import com.diba.beneficiary.shared.messages.events.IEvent;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractAggregate<EVENT extends IEvent, Id> implements Aggregate<Id> {
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

    public abstract void when(EVENT event) throws Exception;

    protected void enqueue(EVENT event) throws Exception {

        uncommittedEvents.add(event);
        when(event);
    }

}