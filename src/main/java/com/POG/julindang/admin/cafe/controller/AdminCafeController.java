package com.POG.julindang.admin.cafe.controller;


import com.POG.julindang.admin.cafe.dto.request.AdminCafeRequestDto;
import com.POG.julindang.admin.cafe.dto.response.AdminCafeResponseDto;
import com.POG.julindang.admin.cafe.dto.request.CafeSaveRequestDto;
import com.POG.julindang.admin.cafe.service.AdminCafeService;
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
@CrossOrigin
public class AdminCafeController {
    private final AdminCafeService adminService;
    @GetMapping("/cafe")
    @Operation(summary = "모든 카페 정보 불러오기",
            description = "모든 카페 정보 로드")
    public ResponseEntity<List<AdminCafeResponseDto>> findAllCafes(){
        return new ResponseEntity(adminService.findAllCafes(), HttpStatus.OK);
    }
    @PostMapping("/cafe/save")
    @Operation(summary = "카페 저장",
            description = "카페 정보 저장")
    public ResponseEntity<CafeSaveRequestDto> saveCafe(@RequestBody CafeSaveRequestDto cafeSaveRequestDto){
        return new ResponseEntity(adminService.saveCafe(cafeSaveRequestDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/cafe/delete/{id}")
    @Operation(summary = "카페 삭제",
            description = "카페 정보 삭제 (deleted만 토글 db에는 저장되어 있음)")
    public ResponseEntity<AdminCafeResponseDto> toggleDeleteCafe(@PathVariable Long id) {
        return new ResponseEntity(adminService.toggleDeleteCafe(id), HttpStatus.OK);
    }
    @PutMapping("/cafe/update")
    @Operation(summary = "카페 정보 업데이트",
            description = "저장되어 있는 카페 정보 업데이트")
    public ResponseEntity<AdminCafeResponseDto> updateCafe(@RequestBody AdminCafeRequestDto adminCafeDto){
        return new ResponseEntity(adminService.updateCafe(adminCafeDto), HttpStatus.OK);
    }


}
