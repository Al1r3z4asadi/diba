
package com.diba.beneficiary.core.command.listener;

import com.eventstore.dbclient.SubscribeToAllOptions;
import com.eventstore.dbclient.SubscribeToStreamOptions;
import com.eventstore.dbclient.SubscriptionFilter;


record SubscriptionOption(
        String subscriptionId,
        boolean ignoreDeserializationErrors ,
        SubscribeToStreamOptions options
) {
    static SubscriptionOption getDefault() {

        SubscribeToStreamOptions options = SubscribeToStreamOptions.get().fromEnd();

        return new SubscriptionOption("command", true, options);
    }
}