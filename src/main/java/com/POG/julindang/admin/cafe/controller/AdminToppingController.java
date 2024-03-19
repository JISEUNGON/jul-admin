package com.POG.julindang.admin.cafe.controller;

import com.POG.julindang.admin.cafe.dto.request.AdminToppingRequestDto;
import com.POG.julindang.admin.cafe.dto.response.AdminToppingResponseDto;

import com.POG.julindang.admin.cafe.dto.request.ToppingSaveRequestDto;
import com.POG.julindang.admin.cafe.service.topping.AdminToppingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminToppingController {
    private final AdminToppingService adminToppingService;

    @PutMapping("/topping/update")
    @Operation(summary = "토핑 정보 업데이트",
            description = "저장되어 있는 토핑 정보 업데이트")
    public ResponseEntity<AdminToppingResponseDto> updateTopping(@RequestBody AdminToppingRequestDto adminToppingRequestDto){
        return new ResponseEntity(adminToppingService.updateTopping(adminToppingRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/topping/delete/{id}")
    @Operation(summary = "토핑 삭제",
            description = "카페 정보 삭제 (deleted만 토글 db에는 저장되어있음)")
    public ResponseEntity<AdminToppingResponseDto> toggleDeleteTopping(@PathVariable Long id) {
        return new ResponseEntity(adminToppingService.toggleDeleteTopping(id), HttpStatus.OK);
    }

    @PostMapping("/topping/save")
    @Operation(summary = "토핑 저장",
            description = "토핑 정보 저장")
    public ResponseEntity<ToppingSaveRequestDto> save(@RequestBody ToppingSaveRequestDto toppingSaveRequestDto) {
        return new ResponseEntity(adminToppingService.saveTopping(toppingSaveRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/topping")
    @Operation(summary = "모든 토핑 정보 불러오기",
            description = "모든 토핑 정보 로드")
    public ResponseEntity<List<AdminToppingResponseDto>> findAllToppings(){
        return new ResponseEntity(adminToppingService.findAllToppings(), HttpStatus.OK);
    }
}
