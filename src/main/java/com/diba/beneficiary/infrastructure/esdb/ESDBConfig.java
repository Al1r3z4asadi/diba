package com.diba.beneficiary.infrastructure.esdb;

import com.diba.beneficiary.core.service.eventbus.IEventBus;
import com.diba.beneficiary.infrastructure.esdb.subscriptions.ISubscriptionCheckpointRepository;
import com.diba.beneficiary.infrastructure.esdb.subscriptions.repository.ESDBCheckpointRepository;
import com.eventstore.dbclient.EventStoreDBClient;
import com.eventstore.dbclient.EventStoreDBClientSettings;
import com.eventstore.dbclient.EventStoreDBConnectionString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ESDBConfig {

    @Bean
    @Scope("singleton")
    EventStoreDBClient eventStoreDBClient(@Value("${esdb.connectionstring}") String connectionString) {
        try {
            EventStoreDBClientSettings settings = EventStoreDBConnectionString.parseOrThrow(connectionString);
            return EventStoreDBClient.create(settings);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    EventListener eventListener(
            EventStoreDBClient eventStore,
            ISubscriptionCheckpointRepository subscriptionCheckpointRepository,
            IEventBus eventBus
    ) {
        return new EventListener(eventStore, subscriptionCheckpointRepository, eventBus);
    }

    @Bean
    ISubscriptionCheckpointRepository subscriptionCheckpointRepository(EventStoreDBClient eventStore) {
        return new ESDBCheckpointRepository(eventStore);
    }

}
