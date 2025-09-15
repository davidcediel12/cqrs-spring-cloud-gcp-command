package com.example.cqrs.gcp.product.command.application.impl;

import com.example.cqrs.gcp.product.command.application.ProductService;
import com.example.cqrs.gcp.product.command.domain.entity.Product;
import com.example.cqrs.gcp.product.command.domain.entity.ProductImage;
import com.example.cqrs.gcp.product.command.domain.exception.ProductNotFoundException;
import com.example.cqrs.gcp.product.command.domain.repository.ProductImageRepository;
import com.example.cqrs.gcp.product.command.domain.repository.ProductRepository;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductDto;
import com.example.cqrs.gcp.product.command.mapper.ProductMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Validated
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto create(@NotNull @Valid ProductDto productDto) {

        Product product = productMapper.toEntity(productDto);
        product = productRepository.save(product);

        for (ProductImage image : product.getImages()) {
            image.setProduct(product);
        }


        productImageRepository.saveAll(product.getImages());

        return productMapper.toDto(product);
    }

    @Override
    public void delete(@NotNull Long productId) {

        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto get(@NotNull Long productId) {

        return productRepository.findById(productId)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with id %s not found", productId)));
    }
}
