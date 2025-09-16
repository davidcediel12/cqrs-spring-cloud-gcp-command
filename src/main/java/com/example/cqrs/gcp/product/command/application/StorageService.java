package com.example.cqrs.gcp.product.command.application;

import com.example.cqrs.gcp.product.command.infrastructure.dto.ImageName;

import java.util.List;

public interface StorageService {


    List<String> generateImageUrls(List<ImageName> imageNames);

}
