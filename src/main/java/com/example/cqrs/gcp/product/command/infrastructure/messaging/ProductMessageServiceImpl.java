package com.example.cqrs.gcp.product.command.infrastructure.messaging;

import com.example.cqrs.gcp.product.command.application.ProductMessageService;
import com.example.cqrs.gcp.product.command.config.PubSubProperties;
import com.example.cqrs.gcp.product.command.domain.entity.Product;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.Message;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.MessageType;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.ProductMessage;
import com.example.cqrs.gcp.product.command.mapper.ProductMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductMessageServiceImpl implements ProductMessageService {


    private final PubSubTemplate pubSubTemplate;
    private final PubSubProperties pubSubProperties;
    private final ProductMapper productMapper;
    private final ObjectMapper objectMapper;


    @Override
    public void notifyNewProduct(Product product) {

        ProductMessage productMessage = productMapper.toMessage(product);

        Message message;
        try {
            message = new Message(MessageType.PRODUCT_CREATED, objectMapper.writeValueAsString(productMessage));
        } catch (JsonProcessingException e) {
            log.error("Error while serializing the product message {}", productMessage, e);
            throw new RuntimeException(e); // TODO change to a dedicated exception
        }

        Map<String, String> attributes = Map.of("event_type", MessageType.PRODUCT_CREATED.name());
        pubSubTemplate.publish(pubSubProperties.getTopic(), message, attributes);


    }
}
