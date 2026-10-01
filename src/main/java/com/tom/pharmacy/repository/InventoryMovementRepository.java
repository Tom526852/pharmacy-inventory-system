package com.tom.pharmacy.repository;

import com.tom.pharmacy.entity.InventoryMovement;
import com.tom.pharmacy.entity.InventoryMovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    List<InventoryMovement> findByMovementTypeOrderByCreatedAtDesc(InventoryMovementType movementType);

    @Query("SELECT COALESCE(SUM(m.totalAmount), 0) FROM InventoryMovement m WHERE m.movementType = :type")
    BigDecimal sumTotalAmountByMovementType(@Param("type") InventoryMovementType type);

    @Query("SELECT COALESCE(SUM(m.totalAmount), 0) FROM InventoryMovement m WHERE m.movementType = :type AND m.createdAt BETWEEN :start AND :end")
    BigDecimal sumTotalAmountByMovementTypeBetween(@Param("type") InventoryMovementType type,
                                                  @Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);

    @Query("SELECT m FROM InventoryMovement m WHERE m.createdAt BETWEEN :start AND :end ORDER BY m.createdAt DESC")
    List<InventoryMovement> findByCreatedAtBetweenOrderByCreatedAtDesc(@Param("start") LocalDateTime start,
                                                                       @Param("end") LocalDateTime end);
}
