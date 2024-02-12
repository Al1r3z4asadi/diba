package com.diba.beneficiary.infrastructure.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
@EnableConfigurationProperties(MyMongoProperties.class)
public class MONGOConfig {

    public static final String PROJECTION_MONGO_TEMPLATE = "projectionMongoTemplate";
    public static final String BENEFICIARY_MONGO_TEMPLATE = "beneficiaryMongoTemplate";

    @Bean(name = {PROJECTION_MONGO_TEMPLATE})
    public ReactiveMongoTemplate projectionMongoTemplate(MongoClient mongoClient, MyMongoProperties mongoProperties) {
        return new ReactiveMongoTemplate(mongoClient, mongoProperties.getProjection().getDatabase());
    }

    @Bean(name = {BENEFICIARY_MONGO_TEMPLATE})
    public ReactiveMongoTemplate localMongoTemplate(MongoClient mongoClient, MyMongoProperties mongoProperties) {
        return new ReactiveMongoTemplate(mongoClient, mongoProperties.getLocal().getDatabase());
    }

    @Bean
    public MongoClient mongoClient(MyMongoProperties mongoProperties) {
        return MongoClients.create(getMongoClientSettings(mongoProperties));
    }

    private MongoClientSettings getMongoClientSettings(MyMongoProperties mongoProperties) {
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(mongoProperties.getUri()))
                .build();
    }

}