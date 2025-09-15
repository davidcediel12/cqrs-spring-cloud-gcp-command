package com.example.cqrs.gcp.product.command.infrastructure.dto;

import java.util.Map;

public record ApiError(
        String code,
        String message,
        Map<String, String> details
) {
}
