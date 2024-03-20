package com.POG.julindang.admin.cafe.repository;

import com.POG.julindang.admin.cafe.domain.MemberRisk;
import com.POG.julindang.admin.cafe.domain.RiskItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRiskRepository extends JpaRepository<MemberRisk, Long> {
    public List<RiskItem> findByMemberId(Long memberId);
}
