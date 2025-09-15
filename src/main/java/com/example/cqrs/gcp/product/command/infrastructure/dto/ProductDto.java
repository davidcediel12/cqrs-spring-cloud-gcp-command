package com.example.cqrs.gcp.product.command.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductDto(
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        Long id,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
        String name,

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
        String description,

        @Positive
        @NotNull
        BigDecimal initialPrice,

        @PositiveOrZero
        @NotNull
        Long stock
) {
}
