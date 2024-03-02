package com.POG.julindang.admin.cafe.dto.request;


import com.POG.julindang.admin.cafe.domain.Topping;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminToppingRequestDto {
    private Long id;
    private String toppingName;
    private Double sugar;
    private Double calorie;
    private String cafeName;
    private String beverageName;
    private Boolean deleted;
    private String manager;

    @Builder
    public AdminToppingRequestDto(Long id, String toppingName, Double sugar, Double calorie, String cafeName, String beverageName, Boolean deleted, String manager) {
        this.id = id;
        this.toppingName = toppingName;
        this.sugar = sugar;
        this.calorie = calorie;
        this.cafeName = cafeName;
        this.beverageName = beverageName;
        this.deleted = deleted;
        this.manager = manager;
    }
    public AdminToppingRequestDto(Topping topping){
        this.id = topping.getId();
        this.toppingName = topping.getToppingName();
        this.sugar = topping.getSugar();
        this.calorie = topping.getCalorie();
        this.cafeName = topping.getCafeName();
        this.beverageName = topping.getBeverageName();
        this.deleted = topping.getDeleted();
        this.manager = topping.getManager();
    }


}
