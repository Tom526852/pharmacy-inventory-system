package com.tom.pharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugResponse {
    private Long id;
    private String drugCode;
    private String name;
    private String genericName;
    private String specification;
    private String category;
    private String dosageForm;
    private Long supplierId;
    private String supplierName;
    private BigDecimal purchasePrice;
    private BigDecimal sellingPrice;
    private Integer currentStock;
    private Integer safetyStock;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
