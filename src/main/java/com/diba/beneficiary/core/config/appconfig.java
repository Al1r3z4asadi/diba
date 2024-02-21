package com.diba.beneficiary.core.config;

import com.diba.beneficiary.core.service.eventbus.EventBus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appconfig {

    @Bean
    EventBus eventBus(ApplicationEventPublisher applicationEventPublisher) {
        return new EventBus(applicationEventPublisher);
    }
}
