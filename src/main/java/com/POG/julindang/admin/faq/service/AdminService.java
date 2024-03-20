package com.POG.julindang.admin.faq.service;


import com.POG.julindang.admin.cafe.domain.FAQ;
import com.POG.julindang.admin.cafe.domain.Inquiry;
import com.POG.julindang.admin.faq.dto.FAQDto;
import com.POG.julindang.admin.faq.dto.FAQSaveDto;
import com.POG.julindang.admin.faq.dto.FAQUpdateDto;
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
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(FAQ.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }

    private void createInquiryTableIfNotExists(){
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(Inquiry.class)
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, createTableRequest);
    }
    public FAQDto saveFAQ(FAQSaveDto faqSaveDto) {
        Date createdAt = new Date();
        dynamoDBMapper.save(faqSaveDto.toEntity(createdAt));
        return FAQDto.builder()
                .title(faqSaveDto.getTitle())
                .context(faqSaveDto.getContext())
                .createdAt(createdAt)
                .build();
    }

    public FAQDto updateFAQ(FAQUpdateDto faqUpdateDto){
        Date createdAt = new Date();
        dynamoDBMapper.save(faqUpdateDto.toEntity(createdAt), new DynamoDBSaveExpression()
                .withExpectedEntry("faq_id",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(faqUpdateDto.getId())
                        )));
        return FAQDto.builder()
                .title(faqUpdateDto.getTitle())
                .context(faqUpdateDto.getContext())
                .createdAt(createdAt)
                .build();
    }

    public FAQDto deleteFAQ(String id){
        FAQ load = dynamoDBMapper.load(FAQ.class, id);
        dynamoDBMapper.save(load, new DynamoDBSaveExpression()
                .withExpectedEntry("faq_id",
                        new ExpectedAttributeValue(
                                new AttributeValue().withS(load.getId())
                        )));

        return FAQDto.builder()
                .id(id)
                .createdAt(load.getCreatedAt())
                .context(load.getContext())
                .title(load.getTitle())
                .build();
    }

}