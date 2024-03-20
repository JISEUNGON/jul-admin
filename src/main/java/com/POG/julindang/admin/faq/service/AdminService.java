package com.POG.julindang.admin.faq.service;


import com.POG.julindang.admin.faq.domain.Faq;
import com.POG.julindang.admin.inquiry.domain.Inquiry;
import com.POG.julindang.admin.faq.dto.FaqDto;
import com.POG.julindang.admin.faq.dto.FaqSaveDto;
import com.POG.julindang.admin.faq.dto.FaqUpdateDto;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final DynamoDBMapper dynamoDBMapper;
    private final AmazonDynamoDB amazonDynamoDB;

    private void createFAQTableIfNotExists() {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Faq.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }

    private void createInquiryTableIfNotExists(){
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Inquiry.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }
    public FaqDto saveFAQ(FaqSaveDto faqSaveDto) {
        Date createdAt = new Date();
        dynamoDBMapper.save(faqSaveDto.toEntity(createdAt));
        return FaqDto.builder()
                .title(faqSaveDto.getTitle())
                .context(faqSaveDto.getContext())
                .createdAt(createdAt)
                .build();
    }

    public FaqDto updateFAQ(FaqUpdateDto faqUpdateDto){
        Date createdAt = new Date();
        dynamoDBMapper.save(faqUpdateDto.toEntity(createdAt), new DynamoDBSaveExpression()
                .withExpectedEntry("faq_id",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(faqUpdateDto.getId())
                        )));
        return FaqDto.builder()
                .title(faqUpdateDto.getTitle())
                .context(faqUpdateDto.getContext())
                .createdAt(createdAt)
                .build();
    }

    public FaqDto deleteFAQ(String id){
        Faq load = dynamoDBMapper.load(Faq.class, id);
        dynamoDBMapper.save(load, new DynamoDBSaveExpression()
                .withExpectedEntry("faq_id",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(load.getId())
                        )));

        return FaqDto.builder()
                .id(id)
                .createdAt(load.getCreatedAt())
                .context(load.getContext())
                .title(load.getTitle())
                .build();
    }

}
