package com.POG.julindang.admin.faq.service;

import com.POG.julindang.admin.faq.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 11/25 추가 : 이메일 서비스
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailSendService {

    private final MailSender mailSender;
    public void sendEmail(EmailDto emailDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("j.julindang@gmail.com");
        simpleMailMessage.setTo("j.julindang@gmail.com");
        simpleMailMessage.setSubject("[줄인당] 문의가 도착했어요");
        simpleMailMessage.setText(emailDto.getUser_email() + " 님의 문의가 도착했습니다. 확인해주세요.");
        mailSender.send(simpleMailMessage);

    }

}