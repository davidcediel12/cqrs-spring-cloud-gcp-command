package com.example.cqrs.gcp.product.command.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.converter.JacksonPubSubMessageConverter;
import com.google.cloud.spring.pubsub.support.converter.PubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubsubConfig {


    @Bean
    public PubSubMessageConverter messageConverter(ObjectMapper objectMapper){
        return new JacksonPubSubMessageConverter(objectMapper);
    }

}
