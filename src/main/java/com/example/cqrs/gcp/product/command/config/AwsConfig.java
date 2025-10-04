package com.example.cqrs.gcp.product.command.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class AwsConfig {



    @Bean
    public S3Client s3Client(AwsProperties awsProperties) {


        return S3Client.builder()
                .region(Region.of(awsProperties.region()))
                .endpointOverride(URI.create("https://s3.us-west-2.amazonaws.com"))
                .forcePathStyle(true)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(awsProperties.accessKeyId(), awsProperties.accessKey())
                ))
                .build();
    }
}
