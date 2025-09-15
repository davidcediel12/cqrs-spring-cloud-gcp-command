package com.example.cqrs.gcp.product.command.domain.entity;

import com.example.cqrs.gcp.product.command.domain.exception.InvalidProductDataException;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal initialPrice;

    @Column(nullable = false)
    private Long stock;


    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime lastModifiedDate;

    @OneToMany(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ProductImage> images;


    public void setId(Long id) {
        this.validateNonNullAndPositive(id, "id");
        this.id = id;
    }

    public void setName(String name) {

        Assert.hasText(name, "Name must not be empty");
        this.name = name;
    }

    public void setDescription(String description) {
        Assert.hasText(description, "Description must not be empty");
        this.description = description;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.validateNonNullAndPositive(initialPrice, "initialPrice");
        this.initialPrice = initialPrice;
    }

    public void setStock(Long stock) {

        this.validateNonNullAndPositive(stock, "stock");
        this.stock = stock;
    }


    private void validateNonNullAndPositive(Number n, String attribute){
        Assert.notNull(n, String.format("%s cannot be null", attribute));

        if(stock < 0){
            throw new InvalidProductDataException(
                    String.format("%s cannot be negative", attribute));
        }

    }
}