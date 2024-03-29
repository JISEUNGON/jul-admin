package com.POG.julindang.admin.member.controller;

import com.POG.julindang.admin.member.dto.response.MemberAllResponseDto;
import com.POG.julindang.admin.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/member")
@RequiredArgsConstructor
@Slf4j
public class MemberAdminController {
    private final MemberService memberService;

    @GetMapping("/search")
    public ResponseEntity<MemberAllResponseDto> findMembers(@RequestParam Long searchType,
                                                            @RequestParam String query,
                                                            @RequestParam Long pageNum) {
        return ResponseEntity.ok(memberService.findALl(searchType, query, pageNum));
    }
}
