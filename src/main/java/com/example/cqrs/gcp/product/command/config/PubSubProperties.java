package com.example.cqrs.gcp.product.command.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.cloud.gcp.pubsub")
@Component
@Getter
@Setter
public class PubSubProperties {

    private String topic;
}
