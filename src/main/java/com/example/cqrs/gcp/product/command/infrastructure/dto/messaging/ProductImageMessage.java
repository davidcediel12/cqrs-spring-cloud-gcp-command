package com.example.cqrs.gcp.product.command.infrastructure.dto.messaging;

public record ProductImageMessage(
        Long id,
        String url,
        Boolean isPrimary
){
}
