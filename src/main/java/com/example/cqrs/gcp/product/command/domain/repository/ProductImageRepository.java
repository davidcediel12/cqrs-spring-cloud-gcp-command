package com.example.cqrs.gcp.product.command.domain.repository;

import com.example.cqrs.gcp.product.command.domain.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
