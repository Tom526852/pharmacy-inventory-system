package com.tom.pharmacy.repository;

import com.tom.pharmacy.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    Optional<Drug> findByDrugCode(String drugCode);
}
