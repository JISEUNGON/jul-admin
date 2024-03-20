package com.POG.julindang.admin.member.service;

import com.POG.julindang.admin.member.domain.Authority;
import com.POG.julindang.admin.member.domain.Member;
import com.POG.julindang.admin.member.repository.MemberRepository;
import com.POG.julindang.common.exception.member.MemberIdNotFoundException;
import com.POG.julindang.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Set<Authority> getAuthorities(final String email) {
        return memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        ).getAuthorities();
    }

    @Override
    @Transactional
    public Set<Authority> upgrade(final String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        Set<Authority> authorities = member.getAuthorities();

        authorities.add(Authority.builder()
                        .authorityName("ROLE_ADMIN")
                        .build());

        return memberRepository.save(
                member
        ).getAuthorities();
    }

    @Override
    @Transactional
    public Set<Authority> downgrade(final String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        Set<Authority> authorities = member.getAuthorities();

        for(Authority authority: authorities) {
            if(authority.getAuthorityName().equals("ROLE_ADMIN")) {
                authorities.remove(authority);
            }
        }

        return memberRepository.save(
                member
        ).getAuthorities();
    }

    @Override
    @Transactional
    public void delete() {
        Member member = memberRepository.findById(JwtUtil.getMemberId()).orElseThrow(
                () -> new MemberIdNotFoundException(JwtUtil.getMemberId())
        );

        member.setDeleted(true);

        memberRepository.save(member);
    }
}
