package com.example.cqrs.gcp.product.command.application.impl;

import com.example.cqrs.gcp.product.command.application.ProductService;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductDto;

public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDto create(ProductDto productDto) {
        return null;
    }

    @Override
    public void delete(Long productId) {

    }

    @Override
    public ProductDto get(Long productId) {
        return null;
    }
}
