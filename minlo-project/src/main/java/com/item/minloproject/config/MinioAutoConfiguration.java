package com.item.minloproject.config;

import com.item.minloproject.pojo.MinioProp;
import com.item.minloproject.template.MinioTemplate;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 装配类
 * @author zcm
 */
@EnableConfigurationProperties(MinioProp.class)
@ConditionalOnProperty(prefix="spring.minio",name = "enabled")
public class MinioAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MinioClient minioClient(MinioProp minioProp) throws InvalidPortException, InvalidEndpointException, InvalidKeyException, ErrorResponseException, IllegalArgumentException, InsufficientDataException, InternalException, InvalidBucketNameException, InvalidResponseException, NoSuchAlgorithmException, XmlParserException, RegionConflictException, IOException {
        MinioClient client = new MinioClient(minioProp.getEndPoint(), minioProp.getAccessKey(), minioProp.getSecretKey());
        if(!client.bucketExists(minioProp.getBucketName()))
        {
            client.makeBucket(minioProp.getBucketName());
        }
        return client;
    }

    @Bean
    @ConditionalOnMissingBean
    public MinioTemplate minioTemplate(MinioProp minioProp,MinioClient minioClient) {
        return new MinioTemplate(minioProp,minioClient);
    }

}
