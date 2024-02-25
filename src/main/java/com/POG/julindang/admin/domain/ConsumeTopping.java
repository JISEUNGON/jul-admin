package com.POG.julindang.admin.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consume_topping")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ConsumeTopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private Long id;

    @Column(name = "consume_id", columnDefinition = "int")
    private Long consumeId;

    @Column(name = "topping_name", columnDefinition = "varchar", length = 30)
    private String toppingName;

    @Column(name = "number", columnDefinition = "varchar", length = 5)
    private String number;
}
