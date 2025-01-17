package com.POG.julindang.common.config;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableDynamoDBRepositories(basePackages = {"com.POG.julindang.admin.repository"}) // DynamoDB Repository 패키지 위치 등록
public class DynamoDBConfig {
    @Value("${amazon.aws.accessKey}")
    private String accessKey;

    @Value("${amazon.aws.secretKey}")
    private String secretKey;

    @Value("${amazon.dynamodb.endpoint}")
    private String endpoint;

    @Value("${amazon.dynamodb.region}")
    private String region;

    @Primary
    @Bean
    public DynamoDBMapper dynamoDbMapper(AmazonDynamoDB amazonDynamoDb) {
        return new DynamoDBMapper(amazonDynamoDb, DynamoDBMapperConfig.DEFAULT);
    }

    @Bean(name = "amazonDynamoDB")
    public AmazonDynamoDB amazonDynamoDb() {
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey));
        EndpointConfiguration endpointConfiguration =
                new EndpointConfiguration(endpoint, region);

        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withEndpointConfiguration(endpointConfiguration).build();
    }

}
