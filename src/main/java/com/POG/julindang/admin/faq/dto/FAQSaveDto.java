package com.POG.julindang.admin.faq.dto;

import com.POG.julindang.admin.cafe.domain.FAQ;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FAQSaveDto {
    private String title;
    private String context;

    @Builder
    public FAQSaveDto(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public FAQ toEntity(Date createdAt){
        return FAQ.builder()
                .title(title)
                .context(context)
                .createdAt(createdAt)
                .build();
    }
}
