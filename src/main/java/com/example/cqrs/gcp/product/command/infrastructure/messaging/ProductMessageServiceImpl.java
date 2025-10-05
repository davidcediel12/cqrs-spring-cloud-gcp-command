package com.example.cqrs.gcp.product.command.infrastructure.messaging;

import com.example.cqrs.gcp.product.command.application.ProductMessageService;
import com.example.cqrs.gcp.product.command.domain.entity.Product;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.Message;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.MessageType;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.ProductMessage;
import com.example.cqrs.gcp.product.command.mapper.ProductMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductMessageServiceImpl implements ProductMessageService {


    private final ProductMapper productMapper;
    private final ObjectMapper objectMapper;
    private final SnsClient snsClient;


    @Override
    public void notifyNewProduct(Product product) {

        ProductMessage productMessage = productMapper.toMessage(product);

        Message message;
        PublishRequest publishRequest;

        try {
            message = new Message(MessageType.PRODUCT_CREATED, productMessage);

            Map<String, MessageAttributeValue> attributes = Map.of(
                    "event_type", MessageAttributeValue.builder()
                            .dataType("String")
                            .stringValue(MessageType.PRODUCT_CREATED.toString())
                            .build());

            publishRequest = PublishRequest.builder()
                    .message(objectMapper.writeValueAsString(message))
                    .topicArn("arn:aws:sns:us-east-2:641675857246:product")
                    .messageAttributes(attributes)
                    .build();

        } catch (JsonProcessingException e) {
            log.error("Error while serializing the product message {}", productMessage, e);
            throw new RuntimeException(e); // TODO change to a dedicated exception
        }


        snsClient.publish(publishRequest);


    }
}
