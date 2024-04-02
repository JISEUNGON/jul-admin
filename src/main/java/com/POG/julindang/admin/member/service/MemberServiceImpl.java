package com.POG.julindang.admin.member.service;

import com.POG.julindang.admin.member.domain.Authority;
import com.POG.julindang.admin.member.domain.Member;
import com.POG.julindang.admin.member.dto.response.MemberAllResponseDto;
import com.POG.julindang.admin.member.vo.MemberInfoVo;
import com.POG.julindang.admin.member.repository.MemberRepository;
import com.POG.julindang.common.exception.common.InvalidArgsException;
import com.POG.julindang.common.exception.member.MemberIdNotFoundException;
import com.POG.julindang.common.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    @Transactional
    public MemberAllResponseDto findALl(final Long searchType, final String query, final Long pageNum) {
        //member info list
        List<MemberInfoVo> memberInfoVoList = new ArrayList<>();
        Long totalPage = 0L;

        if (searchType == 0) { // 이름 + 이메일
            Page<Member> members = memberRepository.findByEmailContainsOrNicknameContains(
                    query,
                    query,
                    PageRequest.of(
                            pageNum.intValue(),
                            6,
                            Sort.by(Sort.Direction.ASC, "nickname")
                    )
            );

            for (Member member: members) {
                memberInfoVoList.add(
                        MemberInfoVo.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .role(member.getRole())
                                .build()
                );
            }

            totalPage = Long.valueOf(members.getTotalPages());
        }
        else if(searchType == 1) { //이름으로 검색
            Page<Member> members = memberRepository.findByNicknameContains(
                    query,
                    PageRequest.of(
                            pageNum.intValue(),
                            6,
                            Sort.by(Sort.Direction.ASC, "nickname")
                    )
            );

            for (Member member: members) {
                memberInfoVoList.add(
                        MemberInfoVo.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .role(member.getRole())
                                .build()
                );
            }

            totalPage = Long.valueOf(members.getTotalPages());
        }
        else if (searchType == 2) { // 이메일로 검색
            Page<Member> members = memberRepository.findByEmailContains(
                    query,
                    PageRequest.of(
                            pageNum.intValue(),
                            6,
                            Sort.by(Sort.Direction.ASC, "nickname")
                    )
            );

            for (Member member: members) {
                memberInfoVoList.add(
                        MemberInfoVo.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .role(member.getRole())
                                .build()
                );
            }

            totalPage = Long.valueOf(members.getTotalPages());
        }
        else
        {
            throw new InvalidArgsException("sortType can not be " + searchType);
        }

        return MemberAllResponseDto.builder()
                .memberInfoVoList(memberInfoVoList)
                .totalPageNumber(totalPage)
                .build();
    }
}
