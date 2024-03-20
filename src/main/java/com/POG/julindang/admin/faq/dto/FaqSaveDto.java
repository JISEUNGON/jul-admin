package com.POG.julindang.admin.faq.dto;

import com.POG.julindang.admin.faq.domain.Faq;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FaqSaveDto {
    private String title;
    private String context;

    @Builder
    public FaqSaveDto(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public Faq toEntity(Date createdAt){
        return Faq.builder()
                .title(title)
                .context(context)
                .createdAt(createdAt)
                .build();
    }
}
