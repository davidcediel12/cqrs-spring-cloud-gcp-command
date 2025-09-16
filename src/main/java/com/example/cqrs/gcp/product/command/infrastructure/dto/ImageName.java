package com.example.cqrs.gcp.product.command.infrastructure.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ImageName(
        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9 ._-]+.(png|jpg|jpeg)$", message = "Invalid file name")
        String name
) {
}
