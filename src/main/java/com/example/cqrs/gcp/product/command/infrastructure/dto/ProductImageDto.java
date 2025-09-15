package com.example.cqrs.gcp.product.command.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProductImageDto(
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        Long id,
        @NotBlank
        @Pattern(regexp = "^https?://.*$")
        String url,
        @NotBlank
        Boolean primary
) {
}
