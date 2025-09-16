package com.example.cqrs.gcp.product.command.application;

import com.example.cqrs.gcp.product.command.domain.entity.Product;
import jakarta.validation.constraints.NotNull;

public interface ProductMessageService {


    void notifyNewProduct(@NotNull Product product);
}
