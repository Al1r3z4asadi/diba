package com.diba.beneficiary.core.config;

import com.diba.beneficiary.core.command.ICommandBus;
import com.diba.beneficiary.core.command.listener.Background;
import com.diba.beneficiary.core.command.listener.CommandListener;
import com.eventstore.dbclient.EventStoreDBClient;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appconfig {

    @Bean
    ICommandBus eventBus(ApplicationEventPublisher applicationEventPublisher) {
        return new CommandListener(applicationEventPublisher);
    }

    @Bean
    Background BackgroundWork(
            EventStoreDBClient eventStore,
            ICommandBus bus
    ) {
        return new Background(eventStore, bus);
    }
}
