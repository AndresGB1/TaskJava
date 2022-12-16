package org.example.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.athena.AthenaClient;

@Configuration
public class AthenaClientFactory {
//    @Bean
//    @Profile("prod")
//    public AthenaClient createClient() {
//        return AthenaClient.builder()
//                .region(Region.AP_SOUTH_1)
//                .credentialsProvider(InstanceProfileCredentialsProvider.create()).build();
//    }

    @Bean
    @Profile("dev")
    public AthenaClient createClientDev() {
        AwsCredentials cred = AwsBasicCredentials.create("key", "password");

        AwsCredentialsProvider credProvider = StaticCredentialsProvider.create(cred);

        return AthenaClient.builder()
                .credentialsProvider(credProvider)
                .region(Region.US_EAST_1)
                .httpClientBuilder(software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient.builder())
                .credentialsProvider(ProfileCredentialsProvider.create()).build();
    }
}
