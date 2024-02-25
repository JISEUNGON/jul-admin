package com.POG.julindang.admin.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "free_consume")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FreeConsume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consume_id", columnDefinition = "int")
    private Long consumeId;

    @Column(name = "name", columnDefinition = "varchar", length = 50)
    private String name;

    @Column(name = "sugar", columnDefinition = "int")
    private Long sugar;

    @Column(name = "calorie", columnDefinition = "int")
    private Long calorie;

    @Column(name = "deleted", columnDefinition = "tinyint")
    private Boolean deleted;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "member_email", columnDefinition = "varchar", length = 50)
    private String memberEmail;

    @Column(name = "created_at", columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
