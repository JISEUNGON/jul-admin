package com.POG.julindang.admin.member.repository;

import com.POG.julindang.admin.member.domain.MemberRisk;
import com.POG.julindang.admin.member.domain.RiskItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRiskRepository extends JpaRepository<MemberRisk, Long> {
    public List<RiskItem> findByMemberId(Long memberId);
}
