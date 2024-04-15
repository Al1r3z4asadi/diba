package com.diba.beneficiary.infrastructure.mongo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@Primary
@EnableConfigurationProperties(MyMongoProperties.class)
@ConfigurationProperties(prefix = "spring.data.mongodb", ignoreUnknownFields = false)
public class MyMongoProperties {
    @NotNull
    private MongoProperties projection;
    @NotNull
    private MongoProperties local;

    @NotNull
    @NotEmpty
    private String uri;
}
