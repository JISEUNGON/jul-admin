package com.POG.julindang.admin.cafe.service.topping;

import com.POG.julindang.admin.cafe.domain.Topping;
import com.POG.julindang.admin.cafe.dto.request.AdminToppingRequestDto;
import com.POG.julindang.admin.cafe.dto.response.AdminToppingResponseDto;
import com.POG.julindang.admin.cafe.dto.request.ToppingSaveRequestDto;
import com.POG.julindang.admin.cafe.repository.ToppingRepository;
import com.POG.julindang.common.exception.cafe.BeverageNameDoesNotExist;
import com.POG.julindang.common.exception.cafe.CafeNameDoesNotExist;
import com.POG.julindang.common.exception.information.ManagerDoesNotExist;
import com.POG.julindang.common.exception.information.SugarDoesNotExist;
import com.POG.julindang.common.exception.topping.ToppingDoesNotExist;
import com.POG.julindang.common.exception.topping.ToppingIdDoesNotExist;
import com.POG.julindang.common.exception.topping.ToppingNameDoesNotExist;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminToppingService {
    private final ToppingRepository toppingRepository;

    public List<AdminToppingResponseDto> findAllToppings(){
        List<Topping> result = toppingRepository.findAll();

        return result.stream()
                .map(AdminToppingResponseDto::new)
                .collect(Collectors.toList());
    }

    public AdminToppingResponseDto toggleDeleteTopping(Long toppingId){
        Topping topping = toppingRepository.findById(toppingId).get();

        topping.setDeleted(!topping.getDeleted());
        return new AdminToppingResponseDto(topping);
    }


    public ToppingSaveRequestDto saveTopping(ToppingSaveRequestDto toppingSaveRequestDto) {
        if(toppingSaveRequestDto.getToppingName() == null) {
            throw new ToppingNameDoesNotExist();
        }

        toppingRepository.save(toppingSaveRequestDto.toEntity());
        return toppingSaveRequestDto;
    }

    public AdminToppingResponseDto updateTopping(AdminToppingRequestDto adminToppingDto) {
        checkToppingDto(adminToppingDto);

        Topping topping = toppingRepository.findById(adminToppingDto.getId())
                .orElseThrow(() -> new ToppingDoesNotExist(adminToppingDto.getId()));


        AdminToppingResponseDto changed = topping.updateTopping(adminToppingDto);
        return changed;
    }

    private void checkToppingDto(AdminToppingRequestDto adminToppingDto){
        Long id = adminToppingDto.getId();
        String toppingName = adminToppingDto.getToppingName();
        String manager = adminToppingDto.getManager();
        Double calorie = adminToppingDto.getCalorie();
        Double sugar = adminToppingDto.getSugar();
        String beverageName = adminToppingDto.getBeverageName();
        String cafeName = adminToppingDto.getCafeName();
        if(id == null){
            throw new ToppingIdDoesNotExist();
        }
        if(toppingName == null){
            throw new ToppingNameDoesNotExist();
        }
        if(manager == null){
            throw new ManagerDoesNotExist();
        }
        if(calorie == null){
            throw new SugarDoesNotExist();
        }
        if(sugar == null){
            throw new SugarDoesNotExist();
        }
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
    }
}
