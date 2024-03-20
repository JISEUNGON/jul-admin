package com.POG.julindang.admin.cafe.service.member;

import com.POG.julindang.admin.member.domain.Authority;

import java.util.Set;

public interface MemberService {
    public Set<Authority> getAuthorities(String email);
    public Set<Authority> upgrade(String email);
    public Set<Authority> downgrade(String email);
    public void delete();
}
