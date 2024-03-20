package com.POG.julindang.admin.cafe.service.cafe;


import com.POG.julindang.admin.cafe.domain.Cafe;
import com.POG.julindang.admin.cafe.dto.request.AdminCafeRequestDto;
import com.POG.julindang.admin.cafe.dto.response.AdminCafeResponseDto;
import com.POG.julindang.admin.cafe.dto.request.CafeSaveRequestDto;
import com.POG.julindang.admin.cafe.repository.CafeRepository;
import com.POG.julindang.common.exception.cafe.*;
import com.POG.julindang.common.exception.information.*;
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
public class AdminCafeService {
    private final CafeRepository cafeRepository;

    public AdminCafeResponseDto toggleDeleteCafe(Long cafeId)  {
        Cafe cafe = cafeRepository.findById(cafeId)
                .orElseThrow(()->new CafeDoesNotExist(cafeId));

        cafe.setDeleted(!cafe.getDeleted());
        return new AdminCafeResponseDto(cafe);
    }

    public List<AdminCafeResponseDto> findAllCafes(){
        List<Cafe> result = cafeRepository.findAll();

        return result.stream()
                .map(AdminCafeResponseDto::new)
                .collect(Collectors.toList());
    }

    public CafeSaveRequestDto saveCafe(CafeSaveRequestDto cafeSaveRequestDto) {
        String cafeName = cafeSaveRequestDto.getCafeName();
        String beverageName = cafeSaveRequestDto.getBeverageName();
        Double calorie = cafeSaveRequestDto.getCalorie();
        Double serve = cafeSaveRequestDto.getServe();
        Double sugar = cafeSaveRequestDto.getSugar();
        String manager = cafeSaveRequestDto.getManager();
        String size = cafeSaveRequestDto.getSize();

        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }
        if(calorie == null){
            throw new CalorieDoesNotExist();
        }
        if(serve == null){
            throw new ServeDoesNotExist();
        }
        if(sugar == null){
            throw new SugarDoesNotExist();
        }
        if(manager == null){
            throw new ManagerDoesNotExist();
        }
        if(size == null){
            throw new SizeDoesNotExist();
        }

        cafeRepository.save(cafeSaveRequestDto.toEntity());
        return cafeSaveRequestDto;
    }

    public AdminCafeResponseDto updateCafe(AdminCafeRequestDto adminCafeDto){
        checkCafeDto(adminCafeDto);
        Cafe cafe = cafeRepository.findById(adminCafeDto.getId())
                .orElseThrow(() -> new CafeDoesNotExist(adminCafeDto.getId()));

        AdminCafeResponseDto changed = cafe.updateCafe(adminCafeDto);
        return changed;
    }

    private void checkCafeDto(AdminCafeRequestDto adminCafeDto){
        Long id = adminCafeDto.getId();
        String cafeName = adminCafeDto.getCafeName();
        String beverageName = adminCafeDto.getBeverageName();
        Double calorie = adminCafeDto.getCalorie();
        Double serve = adminCafeDto.getServe();
        Double sugar = adminCafeDto.getSugar();
        String manager = adminCafeDto.getManager();
        String size = adminCafeDto.getSize();
        if(id == null){
            throw new CafeIdDoesNotExist();
        }
        if(cafeName == null){
            throw new CafeNameDoesNotExist();
        }
        if(beverageName == null){
            throw new BeverageNameDoesNotExist();
        }
        if(calorie == null){
            throw new CalorieDoesNotExist();
        }
        if(serve == null){
            throw new ServeDoesNotExist();
        }
        if(sugar == null){
            throw new SugarDoesNotExist();
        }
        if(manager == null){
            throw new ManagerDoesNotExist();
        }
        if(size == null){
            throw new SizeDoesNotExist();
        }

    }
}

