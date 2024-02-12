package com.diba.beneficiary.infrastructure.mongo;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import static com.diba.beneficiary.infrastructure.mongo.MONGOConfig.BENEFICIARY_MONGO_TEMPLATE;



@EnableReactiveMongoRepositories(
        basePackages = {"com.diba.beneficiary.infrastructure.mongo"},
        reactiveMongoTemplateRef = BENEFICIARY_MONGO_TEMPLATE
)
@Configuration
public class LocalMongoConfig {
}
