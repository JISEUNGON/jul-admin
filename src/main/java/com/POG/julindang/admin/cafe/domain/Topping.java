package com.POG.julindang.admin.cafe.domain;

import com.POG.julindang.admin.cafe.dto.request.AdminToppingRequestDto;
import com.POG.julindang.admin.cafe.dto.response.AdminToppingResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPPING_ID")
    private Long id;

    @NotNull
    private String toppingName;

    @NotNull
    private Double sugar;

    @NotNull
    private Double calorie;

    @NotNull

    private String cafeName;

    @NotNull
    private String beverageName;

    @NotNull
    @ColumnDefault("false")
    private Boolean deleted;

    @NotNull
    private String manager;

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public AdminToppingResponseDto updateTopping(AdminToppingRequestDto adminToppingDto){
        this.beverageName= adminToppingDto.getBeverageName();
        this.calorie = adminToppingDto.getCalorie();
        this.sugar = adminToppingDto.getSugar();
        this.cafeName = adminToppingDto.getCafeName();
        this.toppingName = adminToppingDto.getToppingName();
        this.manager = adminToppingDto.getManager();

        return AdminToppingResponseDto.builder()
                .beverageName(beverageName)
                .cafeName(cafeName)
                .sugar(sugar)
                .calorie(calorie)
                .toppingName(toppingName)
                .manager(manager)
                .build();
    }
}
