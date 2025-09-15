package com.example.cqrs.gcp.product.command.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        Long id,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must be alphanumeric")
        String name,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Description must be alphanumeric")
        String description,

        @Positive
        @NotNull
        BigDecimal initialPrice,

        @PositiveOrZero
        @NotNull
        Long stock,

        List<ProductImageDto> images
) {
}
