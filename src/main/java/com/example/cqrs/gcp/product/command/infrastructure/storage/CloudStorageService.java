package com.example.cqrs.gcp.product.command.infrastructure.storage;

import com.example.cqrs.gcp.product.command.application.StorageService;
import com.example.cqrs.gcp.product.command.config.CloudStorageProperties;
import com.example.cqrs.gcp.product.command.infrastructure.dto.ImageName;
import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ImpersonatedCredentials;
import com.google.auth.oauth2.UserCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.HttpMethod;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
@Validated
public class CloudStorageService implements StorageService {


    private final Storage storage;
    private final CloudStorageProperties cloudStorageProperties;


    @Override
    public List<String> generateImageUrls(List<ImageName> imageNames) {

        Credentials credentialsToSign = getCredentialsToSign();


        return imageNames.stream().map(imageName -> {
                    String imageType = getMediaType(imageName.name());

                    BlobInfo blobInfo = BlobInfo.newBuilder(cloudStorageProperties.getProductImageBucketName(), imageName.name())
                            .setAcl(List.of(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER)))
                            .setContentType(imageType)
                            .build();

                    URL url = storage.signUrl(blobInfo, 5, TimeUnit.MINUTES,
                            Storage.SignUrlOption.httpMethod(HttpMethod.PUT),
                            Storage.SignUrlOption.withContentType(),
                            Storage.SignUrlOption.signWith((ServiceAccountSigner) credentialsToSign));

                    return url.toString();
                })
                .toList();
    }


    private Credentials getCredentialsToSign() {

        Credentials credentials = storage.getOptions().getCredentials();

        if (credentials instanceof UserCredentials) {
            credentials = ImpersonatedCredentials.create((GoogleCredentials) credentials,
                    "blob-signer@thermal-beach-472102-c0.iam.gserviceaccount.com",
                    Collections.emptyList(),
                    Collections.emptyList(),
                    600000);
        }

        return credentials;
    }

    private String getMediaType(String imageName) {

        String mediaType;
        switch (imageName) {
            case ".png" -> mediaType = MediaType.IMAGE_PNG_VALUE;
            case ".jpg", ".jpeg" -> mediaType = MediaType.IMAGE_JPEG_VALUE;
            default -> mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return mediaType;
    }
}
