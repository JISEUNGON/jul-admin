package com.POG.julindang.admin.member.service;

import com.POG.julindang.admin.member.domain.Authority;
import com.POG.julindang.admin.member.domain.Member;
import com.POG.julindang.admin.member.dto.response.MemberAllResponseDto;
import com.POG.julindang.admin.member.vo.MemberInfoVo;
import com.POG.julindang.admin.member.repository.MemberRepository;
import com.POG.julindang.admin.member.vo.UserInfoVo;
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
    public MemberAllResponseDto findMembers(final Long searchType, final String query, final Long pageNum) {
        //member info list
        List<MemberInfoVo> memberInfoVoList = new ArrayList<>();
        Long totalPage = 0L;

        if(query.isBlank() || query.isEmpty()) { //검색어 없을 때
            Page<UserInfoVo> members = memberRepository.findMembersWithUserRole(PageRequest.of(
                    pageNum.intValue(),
                    6,
                    Sort.by(Sort.Direction.ASC, "nickname")
            ));

            for (UserInfoVo member: members) {
                log.info("member id: {}", member.getMemberId());
                memberInfoVoList.add(
                        MemberInfoVo.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .role("관리자")
                                .build()
                );
            }

            totalPage = Long.valueOf(members.getTotalPages());
        }
        else //검색어 있을 떄
        {
            if (searchType == 0) { // 이름 + 이메일
                Page<UserInfoVo> members = memberRepository.findMembersWithUserRoleAndEmailOrNickname(
                        query,
                        query,
                        PageRequest.of(
                                pageNum.intValue(),
                                6,
                                Sort.by(Sort.Direction.ASC, "nickname")
                        )
                );

                for (UserInfoVo member: members) {
                    memberInfoVoList.add(
                            MemberInfoVo.builder()
                                    .memberId(member.getMemberId())
                                    .email(member.getEmail())
                                    .nickname(member.getNickname())
                                    .role("일반 회원")
                                    .build()
                    );
                }

                totalPage = Long.valueOf(members.getTotalPages());
            }
            else if(searchType == 1) { //이름으로 검색
                Page<UserInfoVo> members = memberRepository.findMembersWithUserRoleAndNickname(
                        query,
                        PageRequest.of(
                                pageNum.intValue(),
                                6,
                                Sort.by(Sort.Direction.ASC, "nickname")
                        )
                );

                for (UserInfoVo member: members) {
                    memberInfoVoList.add(
                            MemberInfoVo.builder()
                                    .memberId(member.getMemberId())
                                    .email(member.getEmail())
                                    .nickname(member.getNickname())
                                    .role("일반 회원")
                                    .build()
                    );
                }

                totalPage = Long.valueOf(members.getTotalPages());
            }
            else if (searchType == 2) { // 이메일로 검색
                Page<UserInfoVo> members = memberRepository.findMembersWithUserRoleAndEmail(
                        query,
                        PageRequest.of(
                                pageNum.intValue(),
                                6,
                                Sort.by(Sort.Direction.ASC, "nickname")
                        )
                );

                for (UserInfoVo member: members) {
                    memberInfoVoList.add(
                            MemberInfoVo.builder()
                                    .memberId(member.getMemberId())
                                    .email(member.getEmail())
                                    .nickname(member.getNickname())
                                    .role("일반 회원")
                                    .build()
                    );
                }

                totalPage = Long.valueOf(members.getTotalPages());
            }
            else
            {
                throw new InvalidArgsException("sortType can not be " + searchType);
            }
        }

        return MemberAllResponseDto.builder()
                .memberInfoVoList(memberInfoVoList)
                .totalPageNumber(totalPage)
                .build();
    }

    @Override
    @Transactional
    public MemberAllResponseDto findAdmins(final Long searchType, final String query, final Long pageNum) {
        //admin info list
        List<MemberInfoVo> adminInfoVoList = new ArrayList<>();
        Long totalPage = 0L;

        if(query.isEmpty() || query.isBlank()){ //검색어 없을 때
            Page<UserInfoVo> members = memberRepository.findMembersWithAdminRole(PageRequest.of(
                    pageNum.intValue(),
                    6,
                    Sort.by(Sort.Direction.ASC, "nickname")
            ));

            for (UserInfoVo member: members) {
                adminInfoVoList.add(
                        MemberInfoVo.builder()
                                .memberId(member.getMemberId())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .role("관리자")
                                .build()
                );
            }

            totalPage = Long.valueOf(members.getTotalPages());
        }
        else { //검색어 있을 때
            if (searchType == 0) { // 이름 + 이메일
                Page<UserInfoVo> members = memberRepository.findMembersWithAdminRoleAndEmailOrNickname(
                        query,
                        query,
                        PageRequest.of(
                                pageNum.intValue(),
                                6,
                                Sort.by(Sort.Direction.ASC, "nickname")
                        )
                );

                for (UserInfoVo member: members) {
                    adminInfoVoList.add(
                            MemberInfoVo.builder()
                                    .memberId(member.getMemberId())
                                    .email(member.getEmail())
                                    .nickname(member.getNickname())
                                    .role("관리자")
                                    .build()
                    );
                }

                totalPage = Long.valueOf(members.getTotalPages());
            }
            else if(searchType == 1) { //이름으로 검색
                Page<UserInfoVo> members = memberRepository.findMembersWithAdminRoleAndNickname(
                        query,
                        PageRequest.of(
                                pageNum.intValue(),
                                6,
                                Sort.by(Sort.Direction.ASC, "nickname")
                        )
                );

                for (UserInfoVo member: members) {
                    adminInfoVoList.add(
                            MemberInfoVo.builder()
                                    .memberId(member.getMemberId())
                                    .email(member.getEmail())
                                    .nickname(member.getNickname())
                                    .role("관리자")
                                    .build()
                    );
                }

                totalPage = Long.valueOf(members.getTotalPages());
            }
            else if (searchType == 2) { // 이메일로 검색
                Page<UserInfoVo> members = memberRepository.findMembersWithAdminRoleAndEmail(
                        query,
                        PageRequest.of(
                                pageNum.intValue(),
                                6,
                                Sort.by(Sort.Direction.ASC, "nickname")
                        )
                );

                for (UserInfoVo member: members) {
                    adminInfoVoList.add(
                            MemberInfoVo.builder()
                                    .memberId(member.getMemberId())
                                    .email(member.getEmail())
                                    .nickname(member.getNickname())
                                    .role("관리자")
                                    .build()
                    );
                }

                totalPage = Long.valueOf(members.getTotalPages());
            }
            else
            {
                throw new InvalidArgsException("sortType can not be " + searchType);
            }
        }

        return MemberAllResponseDto.builder()
                .memberInfoVoList(adminInfoVoList)
                .totalPageNumber(totalPage)
                .build();
    }
}
