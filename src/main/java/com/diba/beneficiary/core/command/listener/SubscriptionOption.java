package com.diba.beneficiary.core.command.listener;

import com.eventstore.dbclient.SubscribeToAllOptions;
import com.eventstore.dbclient.SubscriptionFilter;


record SubscriptionOption(
        String subscriptionId,
        boolean ignoreDeserializationErrors,
        SubscribeToAllOptions subscribeToAllOptions
) {
    static SubscriptionOption getDefault() {
        SubscriptionFilter filterOutSystemEvents = SubscriptionFilter.newBuilder()
                .withEventTypeRegularExpression("^[^\\$].*")
                .build();

        SubscribeToAllOptions options = SubscribeToAllOptions.get()
                .fromStart()
                .filter(filterOutSystemEvents);

        return new SubscriptionOption("command", true, options);
    }
}