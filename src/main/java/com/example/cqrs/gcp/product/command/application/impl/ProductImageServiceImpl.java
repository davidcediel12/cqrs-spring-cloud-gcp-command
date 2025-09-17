package com.example.cqrs.gcp.product.command.application.impl;


import com.example.cqrs.gcp.product.command.application.ProductImageService;
import com.example.cqrs.gcp.product.command.application.StorageService;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ImageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final StorageService storageService;

    @Override
    public List<String> generateImages(List<ImageName> imageNames) {

        return storageService.generateImageUrls(imageNames);
    }
}
