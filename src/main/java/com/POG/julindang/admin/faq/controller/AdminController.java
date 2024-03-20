package com.POG.julindang.admin.faq.controller;


import com.POG.julindang.admin.faq.dto.FaqDto;
import com.POG.julindang.admin.faq.dto.FaqSaveDto;
import com.POG.julindang.admin.faq.dto.FaqUpdateDto;
import com.POG.julindang.admin.faq.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/faq/save")
    public ResponseEntity<FaqDto> saveFAQ(@RequestBody FaqSaveDto faqSaveDto){
        return new ResponseEntity<>(adminService.saveFAQ(faqSaveDto), HttpStatus.OK);
    }
    @PostMapping("/faq/update")
    public ResponseEntity<FaqDto> updateFAQ(@RequestBody FaqUpdateDto faqUpdateDto){
        return new ResponseEntity<>(adminService.updateFAQ(faqUpdateDto), HttpStatus.OK);
    }
    @DeleteMapping("/faq/delete/{id}")
    public ResponseEntity<FaqDto> deleteFAQ(@PathVariable String id){
        return new ResponseEntity<>(adminService.deleteFAQ(id), HttpStatus.OK);
    }
}
