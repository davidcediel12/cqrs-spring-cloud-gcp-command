package com.example.cqrs.gcp.product.command.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.cloud.gcp.storage")
@Component
@Getter
@Setter
public class CloudStorageProperties {

    private String productImageBucketName;
}
