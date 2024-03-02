package com.POG.julindang.admin.cafe.repository;

import com.POG.julindang.admin.cafe.domain.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CafeRepository extends JpaRepository <Cafe, Long>{
    List<Cafe> findByBeverageName(@Param("beverageName") String beverageName);
    List<Cafe> findByCafeName(@Param("cafeName") String cafeName);
    Optional<Cafe> findByCafeNameAndBeverageNameAndSize(@Param("cafeName") String cafeName, @Param("beverageName") String beverageName, @Param("size") String size);
    void deleteById(@Param("cafeId") Long id);
}
