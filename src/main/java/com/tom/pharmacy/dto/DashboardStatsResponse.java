package com.tom.pharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsResponse {
    private long totalDrugs;
    private long totalSuppliers;
    private int totalInventory;
    private long lowStockCount;
    private BigDecimal totalRevenue;
    private BigDecimal totalPurchaseCost;
    private BigDecimal totalProfit;
    private LocalDateTime generatedAt;
}
