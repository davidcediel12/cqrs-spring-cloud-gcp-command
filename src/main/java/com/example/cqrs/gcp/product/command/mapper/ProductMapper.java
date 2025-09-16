package com.example.cqrs.gcp.product.command.mapper;


import com.example.cqrs.gcp.product.command.domain.entity.Product;
import com.example.cqrs.gcp.product.command.domain.entity.ProductImage;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductDto;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductImageDto;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.ProductImageMessage;
import com.example.cqrs.gcp.product.command.infrastructure.dto.messaging.ProductMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product product);

    ProductImage toEntity(ProductImageDto productImageDto);

    ProductImageDto toDto(ProductImage product);


    List<ProductImageDto> toImageDtos(List<ProductImage> productImages);

    List<ProductImage> toImageEntities(List<ProductImageDto> productImageDtos);


    List<ProductImageMessage> toImagesMessage(List<ProductImage> images);
    ProductImageMessage toImageMessage(ProductImage image);


    ProductMessage toMessage(Product product);
}
