package com.POG.julindang.admin.cafe.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Builder
@DynamoDBTable(tableName = "faq")
@AllArgsConstructor
@NoArgsConstructor
@Setter // Setters are used in aws-dynamodb-sdk
public class FAQ {
    @DynamoDBHashKey(attributeName = "faq_id")
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String title;

    @DynamoDBAttribute
    private String context;

    @DynamoDBRangeKey(attributeName = "created_at")
    @DynamoDBAttribute
    private Date createdAt;
}