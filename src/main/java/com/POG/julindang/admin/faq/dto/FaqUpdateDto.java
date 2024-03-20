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
public class FaqUpdateDto {
    private String id;
    private String title;
    private String context;

    @Builder
    public FaqUpdateDto(String id, String title, String context) {
        this.id= id;
        this.title = title;
        this.context = context;
    }

    public Faq toEntity(Date createdAt){
        return Faq.builder()
                .id(id)
                .title(title)
                .context(context)
                .createdAt(createdAt)
                .build();
    }
}
