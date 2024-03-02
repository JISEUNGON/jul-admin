package com.POG.julindang.admin.faq.dto;


import com.POG.julindang.domain.FAQ;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FAQDto {
    private String id;
    private String title;
    private String context;
    private Date createdAt;
    @Builder
    public FAQDto(String id, String title, String context, Date createdAt) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.createdAt = createdAt;
    }

    public FAQDto(FAQ faq){
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.context = faq.getContext();
        this.createdAt = faq.getCreatedAt();
    }
    public FAQ toEntity(){
        return FAQ.builder()
                .id(id)
                .context(context)
                .createdAt(new Date())
                .title(title)
                .build();
    }
}
