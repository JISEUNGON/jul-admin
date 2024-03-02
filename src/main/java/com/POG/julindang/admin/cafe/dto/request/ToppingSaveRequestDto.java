package com.POG.julindang.admin.cafe.dto.request;

import com.POG.julindang.admin.cafe.domain.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ToppingSaveRequestDto {
    private String toppingName;
    private Double sugar;
    private Double calorie;
    private String cafeName;
    private String beverageName;
    private String manager;

    @Builder
    public ToppingSaveRequestDto(String toppingName, Double sugar, Double calorie, String cafeName, String beverageName, String manager) {
        this.toppingName = toppingName;
        this.sugar = sugar;
        this.calorie = calorie;
        this.cafeName = cafeName;
        this.beverageName = beverageName;
        this.manager = manager;
    }

    public Topping toEntity(){
        return Topping.builder()
                .sugar(sugar)
                .beverageName(beverageName)
                .cafeName(cafeName)
                .manager(manager)
                .toppingName(toppingName)
                .calorie(calorie)
                .build();
    }
}
