package com.POG.julindang.admin.cafe.domain;



import com.POG.julindang.admin.cafe.dto.request.AdminCafeRequestDto;
import com.POG.julindang.admin.cafe.dto.response.AdminCafeResponseDto;
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
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAFE_ID")
    private Long id;
    @NotNull
    @Column(name="CAFE_NAME", columnDefinition = "varchar", length = 30)
    private String cafeName;
    @NotNull
    @Column(name = "BEVERAGE_NAME", columnDefinition = "varchar", length = 50)
    private String beverageName;
    @NotNull
    @Column(name="SIZE", columnDefinition = "varchar", length = 10)
    private String size;
    @NotNull
    private Double serve;
    @NotNull
    private Double sugar;
    @NotNull
    private Double calorie;
    @NotNull
    private Boolean temperature;
    @NotNull
    @Column(name="MANAGER", columnDefinition = "varchar", length = 10)
    private String manager;
    @NotNull
    @ColumnDefault("false")
    private Boolean deleted;
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public AdminCafeResponseDto updateCafe(AdminCafeRequestDto adminCafeDto) {
        this.cafeName = adminCafeDto.getCafeName();
        this.beverageName = adminCafeDto.getBeverageName();
        this.size = adminCafeDto.getSize();
        this.serve = adminCafeDto.getServe();
        this.sugar = adminCafeDto.getSugar();
        this.calorie = adminCafeDto.getCalorie();
        this.temperature = adminCafeDto.getTemperature();
        this.manager = adminCafeDto.getManager();

        return AdminCafeResponseDto.builder()
                .beverageName(beverageName)
                .cafeName(cafeName)
                .serve(serve)
                .size(size)
                .calorie(calorie)
                .deleted(deleted)
                .manager(manager)
                .temperature(temperature)
                .build();
    }
}
