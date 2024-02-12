package com.diba.beneficiary.report;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import static com.diba.beneficiary.infrastructure.mongo.MONGOConfig.PROJECTION_MONGO_TEMPLATE;


@Configuration
@EnableReactiveMongoRepositories(
        basePackages = {"com.diba.beneficiary.report.repositories"},
        reactiveMongoTemplateRef = PROJECTION_MONGO_TEMPLATE
)

public class ReportMongoConfig {
}
