package com.POG.julindang.admin.faq.dto;

import com.POG.julindang.domain.FAQ;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FAQUpdateDto {
    private String id;
    private String title;
    private String context;

    @Builder
    public FAQUpdateDto(String id, String title, String context) {
        this.id= id;
        this.title = title;
        this.context = context;
    }

    public FAQ toEntity(Date createdAt){
        return FAQ.builder()
                .id(id)
                .title(title)
                .context(context)
                .createdAt(createdAt)
                .build();
    }
}
