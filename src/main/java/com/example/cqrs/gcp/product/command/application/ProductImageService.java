package com.example.cqrs.gcp.product.command.application;

import com.example.cqrs.gcp.product.command.infrastructure.dto.ImageName;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface ProductImageService {


    List<String> generateImages(@NotNull List<ImageName> imageNames);
}
