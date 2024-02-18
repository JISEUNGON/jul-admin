package com.POG.julindang.admin.controller;

import com.POG.julindang.admin.dto.EmailDto;
import com.POG.julindang.admin.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/email")
public class EmailController {
    private final EmailSendService emailSendService;
    @PostMapping
    public ResponseEntity sendMain(@RequestBody EmailDto emailDto){
        emailSendService.sendEmail(emailDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
