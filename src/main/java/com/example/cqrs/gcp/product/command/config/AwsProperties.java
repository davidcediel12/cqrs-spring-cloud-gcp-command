package com.example.cqrs.gcp.product.command.config;


import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.cloud.aws")
public record AwsProperties(
        @NotEmpty
        String accessKeyId,
        @NotEmpty
        String accessKey,
        @NotEmpty
        String region,
        @NotEmpty
        String bucketName
) {
}
