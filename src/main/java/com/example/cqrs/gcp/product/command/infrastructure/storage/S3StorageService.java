package com.example.cqrs.gcp.product.command.infrastructure.storage;

import com.example.cqrs.gcp.product.command.application.StorageService;
import com.example.cqrs.gcp.product.command.config.AwsProperties;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ImageName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class S3StorageService implements StorageService {


    private final S3Presigner s3Presigner;
    private final AwsProperties awsProperties;

    @Override
    public List<String> generateImageUrls(List<ImageName> imageNames) {

        List<String> urls = new ArrayList<>();


        for (ImageName imageName : imageNames) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(awsProperties.bucketName())
                    .key("public/" + imageName.name())
                    .contentType(MediaType.IMAGE_PNG_VALUE)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(5))
                    .putObjectRequest(putObjectRequest)
                    .build();


            PresignedPutObjectRequest presignedRequest = s3Presigner
                    .presignPutObject(presignRequest);

            String url = presignedRequest.url().toExternalForm();

            log.info("Generated presigned url {}", url);
            log.info("Method: {}", presignedRequest.httpRequest().method());

            urls.add(url);

        }
        return urls;
    }
}
