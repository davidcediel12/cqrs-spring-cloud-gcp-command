package com.example.cqrs.gcp.product.command.domain.repository;

import com.example.cqrs.gcp.product.command.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
