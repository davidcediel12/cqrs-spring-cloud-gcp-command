package com.example.cqrs.gcp.product.command.infrastructure.dto.messaging;

public record Message(MessageType type, String payload) {
}
