package com.example.cqrs.gcp.product.command.mapper;


import com.example.cqrs.gcp.product.command.domain.entity.Product;
import com.example.cqrs.gcp.product.command.domain.entity.ProductImage;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductDto;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ProductImageDto;
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
}
