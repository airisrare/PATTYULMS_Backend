package com.example.pattyulms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;


@Configuration
public class s3Config {

    @Value("${aws.access.key.id}")
    private String accessKey;

    @Value("${aws.access.key.id}")
    private String secretKey;

    // @Value("${aws.s3.region}")
    // private String region;

    @Bean
    public S3Client s3Client(){
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, accessKey);
        
        return S3Client.builder().region(Region.US_WEST_1).credentialsProvider(StaticCredentialsProvider.create(awsCredentials)).build();
    }

    
}
