package com.example.cqrs.gcp.product.command.infrastructure.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ImageNames(@Valid @NotEmpty List<ImageName> imageNames) {
}
