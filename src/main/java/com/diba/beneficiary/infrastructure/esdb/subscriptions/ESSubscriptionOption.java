package com.diba.beneficiary.infrastructure.esdb.subscriptions;


import com.eventstore.dbclient.SubscribeToAllOptions;
import com.eventstore.dbclient.SubscriptionFilter;

record ESSubscriptionOption(
        String subscriptionId,
        boolean ignoreDeserializationErrors,
        SubscribeToAllOptions subscribeToAllOptions
) {
    static ESSubscriptionOption getDefault() {
        SubscriptionFilter filterOutSystemEvents = SubscriptionFilter.newBuilder()
                .withEventTypeRegularExpression("^[^\\$].*")
                .build();

        SubscribeToAllOptions options = SubscribeToAllOptions.get()
                .fromStart()
                .filter(filterOutSystemEvents);

        return new ESSubscriptionOption("default", true, options);
    }
}