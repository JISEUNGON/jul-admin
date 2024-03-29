package com.POG.julindang.admin.member.repository;

import com.POG.julindang.admin.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByAccessTokenAndMemberId(String token, Long memberId);
    public Page<Member> findByNicknameContains(String query, PageRequest pageRequest);
    public Page<Member> findByEmailContains(String query, PageRequest of);
    public Page<Member> findByEmailContainsOrNicknameContains(String email, String nickname, PageRequest of);
}
