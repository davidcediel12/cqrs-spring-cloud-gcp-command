package com.example.cqrs.gcp.product.command.infrastructure.controller;


import com.example.cqrs.gcp.product.command.application.ProductImageService;
import com.example.cqrs.gcp.product.command.application.ProductService;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ImageNames;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;


    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto) {

        return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long id) {

        return ResponseEntity.ok(productService.get(id));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PostMapping("/images")
    public ResponseEntity<List<String>> generateImageUrls(@Valid @RequestBody ImageNames imageNames) {

        return ResponseEntity.ok(productImageService.generateImages(imageNames.imageNames()));
    }


}
