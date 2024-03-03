package com.diba.beneficiary.core.models.order;

import com.diba.beneficiary.infrastructure.esdb.EventStoreRepository;
import com.diba.beneficiary.shared.messages.events.OrderEvent;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@Configuration
public class OrderConfig {
    @Bean
    @ApplicationScope
    EventStoreRepository<Order, OrderEvent, UUID> OrderStore(EventStoreDBClient eventStore) {
        return new EventStoreRepository<>(
                eventStore,
                Order::mapToStreamId,
                Order::empty
        );
    }
}
