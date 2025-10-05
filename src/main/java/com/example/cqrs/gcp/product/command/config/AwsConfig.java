package com.example.cqrs.gcp.product.command.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.sns.SnsClient;


@Configuration
public class AwsConfig {


    @Bean
    public S3Client s3Client(AwsProperties awsProperties) {


        return S3Client.builder()
                .region(Region.of(awsProperties.region()))
                .credentialsProvider(getStaticCredentials(awsProperties))
                .build();
    }


    @Bean
    public S3Presigner s3Presigner(AwsProperties awsProperties) {
        return S3Presigner.builder()
                .region(Region.of(awsProperties.region()))
                .credentialsProvider(getStaticCredentials(awsProperties))
                .build();
    }


    @Bean
    public SnsClient snsClient(AwsProperties awsProperties) {
        return SnsClient.builder()
                .region(Region.of(awsProperties.region()))
                .credentialsProvider(getStaticCredentials(awsProperties))
                .build();
    }

    private StaticCredentialsProvider getStaticCredentials(AwsProperties awsProperties) {
        return StaticCredentialsProvider.create(
                AwsBasicCredentials.create(awsProperties.accessKeyId(), awsProperties.accessKey())
        );
    }
}
