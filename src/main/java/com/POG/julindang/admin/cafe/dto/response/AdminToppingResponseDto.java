package com.POG.julindang.admin.cafe.dto.response;


import com.POG.julindang.admin.cafe.domain.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminToppingResponseDto {
    private Long id;
    private String toppingName;
    private Double sugar;
    private Double calorie;
    private String cafeName;
    private String beverageName;
    private String manager;
    private Boolean deleted;
    @Builder
    public AdminToppingResponseDto(String toppingName, Double sugar, Double calorie, String cafeName, String beverageName, String manager) {
        this.toppingName = toppingName;
        this.sugar = sugar;
        this.calorie = calorie;
        this.cafeName = cafeName;
        this.beverageName = beverageName;
        this.manager = manager;
    }
    public AdminToppingResponseDto(Topping topping){
        this.id = topping.getId();
        this.toppingName = topping.getToppingName();
        this.sugar = topping.getSugar();
        this.calorie = topping.getCalorie();
        this.cafeName = topping.getCafeName();
        this.beverageName = topping.getBeverageName();
        this.manager = topping.getManager();
        this.deleted = topping.getDeleted();
    }


}
