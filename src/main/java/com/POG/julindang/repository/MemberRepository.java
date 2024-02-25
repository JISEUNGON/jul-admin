package com.POG.julindang.repository;

import com.POG.julindang.admin.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByNickname(String nickname);
    public Page<Member> findByDeletedIsFalse(Pageable pageable);
    public Optional<Member> findByAccessTokenAndMemberId(String token, Long id);
}
