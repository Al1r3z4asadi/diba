package com.diba.beneficiary.core.models;


public interface Aggregate<Id> {
    Id id();
    int version();

    Object[] dequeueUncommittedEvents();
}