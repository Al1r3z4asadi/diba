package com.diba.beneficiary.core.models.Beneficiary;

import com.diba.beneficiary.infrastructure.esdb.EventStoreRepository;
import com.diba.beneficiary.shared.messages.events.BeneficiaryEvents;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@Configuration

public class BeneficiaryConfig {

    @Bean
    @ApplicationScope
    EventStoreRepository<Beneficiary, BeneficiaryEvents, UUID> beneficiaryStore(EventStoreDBClient eventStore) {
        return new EventStoreRepository<>(eventStore, Beneficiary::mapToStreamId, Beneficiary::empty);
    }
}
