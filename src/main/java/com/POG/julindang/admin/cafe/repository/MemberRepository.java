package com.POG.julindang.admin.cafe.repository;

import com.POG.julindang.admin.cafe.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByNickname(String nickname);
    public Optional<Member> findByAccessTokenAndMemberId(String token, Long memberId);
}