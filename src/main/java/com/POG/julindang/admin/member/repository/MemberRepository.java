package com.POG.julindang.admin.member.repository;

import com.POG.julindang.admin.member.domain.Member;
import com.POG.julindang.admin.member.vo.UserInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Optional<Member> findByEmail(String email);
    public Optional<Member> findByAccessTokenAndMemberId(String token, Long memberId);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where authority_name = 'ROLE_USER') and m.nickname = :nickname")
    public Page<UserInfoVo> findMembersWithUserRoleAndNickname(@Param(value = "nickname") String nickname, PageRequest pageRequest);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where authority_name = 'ROLE_USER') and m.email = :email")
    public Page<UserInfoVo> findMembersWithUserRoleAndEmail(@Param(value = "email") String email, PageRequest pageRequest);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where ma.authority_name = 'ROLE_USER') and m.email = :email or m.nickname = :nickname")
    public Page<UserInfoVo> findMembersWithUserRoleAndEmailOrNickname(@Param(value = "email") String email, @Param(value = "nickname") String nickname, PageRequest pageRequest);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where authority_name = 'ROLE_User')")
    public Page<UserInfoVo> findMembersWithUserRole(PageRequest nickname);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where authority_name = 'ROLE_ADMIN') and m.nickname = :nickname")
    public Page<UserInfoVo> findMembersWithAdminRoleAndNickname(@Param(value = "nickname") String nickname, PageRequest pageRequest);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where authority_name = 'ROLE_ADMIN') and m.email = :email")
    public Page<UserInfoVo> findMembersWithAdminRoleAndEmail(@Param(value = "email") String email, PageRequest pageRequest);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where authority_name = 'ROLE_ADMIN') and m.email = :email or m.nickname = :nickname")
    public Page<UserInfoVo> findMembersWithAdminRoleAndEmailOrNickname(@Param(value = "email") String email, @Param(value = "nickname") String nickname, PageRequest pageRequest);

    @Query(nativeQuery = true, value = "select m.member_id memberId, m.email, m.nickname " +
            "from member m " +
            "where m.member_id in (" +
            "select ma.member_id " +
            "from member_authority ma " +
            "where authority_name = 'ROLE_ADMIN')")
    public Page<UserInfoVo> findMembersWithAdminRole(PageRequest nickname);
}
