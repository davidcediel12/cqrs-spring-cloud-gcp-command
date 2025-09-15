package com.example.cqrs.gcp.product.command.domain;

import com.example.cqrs.gcp.product.command.domain.exception.InvalidProductDataException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

@Getter
@Entity
@Table(name = "product_image")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private Boolean primary;


    public void setId(Long id) {
        Assert.notNull(id, "id cannot be null");
        this.id = id;
    }

    public void setUrl(String url) {
        Assert.hasText(url, "url cannot be empty");

        if(!url.matches("^https?://.*")){
            throw new InvalidProductDataException("url must be a valid url");
        }


        this.url = url;
    }

    public void setPrimary(Boolean primary) {

        Assert.notNull(primary, "primary cannot be null");
        this.primary = primary;
    }
}