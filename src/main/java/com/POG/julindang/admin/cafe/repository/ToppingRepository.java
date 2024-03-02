package com.POG.julindang.admin.cafe.repository;

import com.POG.julindang.admin.cafe.domain.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ToppingRepository extends JpaRepository<Topping, Long> {
    Optional<Topping> findById(@Param("id") Long id);
}
