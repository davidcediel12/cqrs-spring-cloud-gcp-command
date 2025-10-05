package com.example.cqrs.gcp.product.command.infrastructure.dto.messaging;

import java.util.List;

public record ProductMessage  (
        Long id,
        String name,
        String description,
        Long initialPrice,
        Long stock,
        List<ProductImageMessage> images

) implements MessagePayload {
}
