package com.POG.julindang.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "consume")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Consume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consume_id", columnDefinition = "bigint")
    private Long consumeId;

    @Column(name = "cafe_name", columnDefinition = "varchar", length = 30)
    private String cafeName;

    @Column(name = "beverage_name", columnDefinition = "varchar", length = 50)
    private String beverageName;

    @Column(name = "hot", columnDefinition = "tinyint")
    private Boolean hot;

    @Column(name = "volume", columnDefinition = "int")
    private Long volume;

    @Column(name = "size", columnDefinition = "varchar", length = 10)
    private String size;

    @Column(name = "sugar", columnDefinition = "int")
    private Long sugar;

    @Column(name = "calorie", columnDefinition = "int")
    private Long calorie;

    @Column(name = "member_id", columnDefinition = "bigint")
    private Long memberId;

    @Column(name = "member_email", columnDefinition = "varchar", length = 50)
    private String memberEmail;

    @Column(name = "created_at", columnDefinition = "datetime")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "deleted", columnDefinition = "tinyint")
    private Boolean deleted;
}
