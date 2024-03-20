package com.POG.julindang.admin.inquiry.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long id;

    @NotNull
    private String sender;

    @NotNull
    private String category;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String question;

    @CreationTimestamp
    @NotNull
    private Date createdAt;

    @NotNull
    @ColumnDefault("false")
    private Boolean deleted;

    @Column(columnDefinition = "TEXT")
    private String answer;

    private String manager;
}