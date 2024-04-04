package com.POG.julindang.admin.member.controller;

import com.POG.julindang.admin.member.dto.response.MemberAllResponseDto;
import com.POG.julindang.admin.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Member 관련 API")
public class MemberAdminController {
    private final MemberService memberService;

    @GetMapping("/search/member")
    @Operation(summary = "멤버 조회 + 검색 API")
    @Parameters(value = {
            @Parameter(name = "searchType", description = "0: 이름 + 이메일로 검색, 1: 이름으로 검색, 2: 이메일로 검색", required = true),
            @Parameter(name = "query", description = "검색어(안적어도 됨)", required = false, allowEmptyValue = true),
            @Parameter(name = "pageNum", description = "페이지 번호, 0번 부터 시작", required = true)
    })
    public ResponseEntity<MemberAllResponseDto> findMembers(@RequestParam Long searchType,
                                                            @RequestParam String query,
                                                            @RequestParam Long pageNum) {
        return ResponseEntity.ok(memberService.findMembers(searchType, query, pageNum));
    }

    @GetMapping("/search/admin")
    @Operation(summary = "관리자 조회 + 검색 API")
    @Parameters(value = {
            @Parameter(name = "searchType", description = "0: 이름 + 이메일로 검색, 1: 이름으로 검색, 2: 이메일로 검색", required = true),
            @Parameter(name = "query", description = "검색어(안적어도 됨", required = false, allowEmptyValue = true),
            @Parameter(name = "pageNum", description = "페이지 번호, 0번 부터 시작", required = true)
    })
    public ResponseEntity<MemberAllResponseDto> findAdmins(@RequestParam Long searchType,
                                                            @RequestParam String query,
                                                            @RequestParam Long pageNum) {
        return ResponseEntity.ok(memberService.findAdmins(searchType, query, pageNum));
    }
}
