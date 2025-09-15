package com.example.cqrs.gcp.product.command.application;

import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ProductService {


    ProductDto create(@NotNull @Valid ProductDto productDto);

    void delete(@NotNull Long productId);

    ProductDto get(@NotNull Long productId);
}
