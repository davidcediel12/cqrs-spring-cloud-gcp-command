package com.example.cqrs.gcp.product.command.application;

import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductDto;

public interface ProductService {


    ProductDto create(ProductDto productDto);

    void delete(Long productId);

    ProductDto get(Long productId);
}
