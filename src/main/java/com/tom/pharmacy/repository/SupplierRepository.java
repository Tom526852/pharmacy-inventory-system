package com.tom.pharmacy.repository;

import com.tom.pharmacy.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
