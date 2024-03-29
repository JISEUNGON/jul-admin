package com.POG.julindang.admin.member.service;

import com.POG.julindang.admin.member.domain.Authority;
import com.POG.julindang.admin.member.dto.response.MemberAllResponseDto;

import java.util.Set;

public interface MemberService {
    public Set<Authority> getAuthorities(String email);
    public Set<Authority> upgrade(String email);
    public Set<Authority> downgrade(String email);
    public void delete();
    public MemberAllResponseDto findALl(Long sortType, String query, Long pageNum);
}
