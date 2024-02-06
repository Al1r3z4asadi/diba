
package com.diba.beneficiary.temp.listener;

import com.eventstore.dbclient.SubscribeToStreamOptions;


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