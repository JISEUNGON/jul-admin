package com.POG.julindang.admin.faq.dto;

import com.POG.julindang.admin.faq.domain.Faq;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FaqDto {
    private String id;
    private String title;
    private String context;
    private Date createdAt;
    @Builder
    public FaqDto(String id, String title, String context, Date createdAt) {
        this.id = id;
        this.title = title;
        this.context = context;
        this.createdAt = createdAt;
    }

    public FaqDto(Faq faq){
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.context = faq.getContext();
        this.createdAt = faq.getCreatedAt();
    }
    public Faq toEntity(){
        return Faq.builder()
                .id(id)
                .context(context)
                .createdAt(new Date())
                .title(title)
                .build();
    }
}
