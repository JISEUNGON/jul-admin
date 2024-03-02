package com.POG.julindang.admin.faq.controller;


import com.POG.julindang.admin.faq.dto.FAQDto;
import com.POG.julindang.admin.faq.dto.FAQSaveDto;
import com.POG.julindang.admin.faq.dto.FAQUpdateDto;
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
    public ResponseEntity<FAQDto> saveFAQ(@RequestBody FAQSaveDto faqSaveDto){
        return new ResponseEntity<>(adminService.saveFAQ(faqSaveDto), HttpStatus.OK);
    }
    @PostMapping("/faq/update")
    public ResponseEntity<FAQDto> updateFAQ(@RequestBody FAQUpdateDto faqUpdateDto){
        return new ResponseEntity<>(adminService.updateFAQ(faqUpdateDto), HttpStatus.OK);
    }
    @DeleteMapping("/faq/delete/{id}")
    public ResponseEntity<FAQDto> deleteFAQ(@PathVariable String id){
        return new ResponseEntity<>(adminService.deleteFAQ(id), HttpStatus.OK);
    }
}
