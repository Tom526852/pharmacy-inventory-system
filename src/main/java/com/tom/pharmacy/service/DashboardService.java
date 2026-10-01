package com.tom.pharmacy.service;

import com.tom.pharmacy.dto.DashboardStatsResponse;
import com.tom.pharmacy.entity.Drug;
import com.tom.pharmacy.entity.InventoryMovementType;
import com.tom.pharmacy.repository.DrugRepository;
import com.tom.pharmacy.repository.InventoryMovementRepository;
import com.tom.pharmacy.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final DrugRepository drugRepository;
    private final SupplierRepository supplierRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    public DashboardStatsResponse getDashboardStats() {
        long totalDrugs = drugRepository.count();
        long totalSuppliers = supplierRepository.count();

        int totalInventory = drugRepository.findAll().stream()
                .mapToInt(Drug::getCurrentStock)
                .sum();

        long lowStockCount = drugRepository.findAll().stream()
                .filter(drug -> drug.getCurrentStock() <= drug.getSafetyStock())
                .count();

        BigDecimal totalRevenue = inventoryMovementRepository.sumTotalAmountByMovementType(InventoryMovementType.SALE);
        BigDecimal totalPurchaseCost = inventoryMovementRepository.sumTotalAmountByMovementType(InventoryMovementType.INBOUND);

        DashboardStatsResponse response = new DashboardStatsResponse();
        response.setTotalDrugs(totalDrugs);
        response.setTotalSuppliers(totalSuppliers);
        response.setTotalInventory(totalInventory);
        response.setLowStockCount(lowStockCount);
        response.setTotalRevenue(totalRevenue == null ? BigDecimal.ZERO : totalRevenue);
        response.setTotalPurchaseCost(totalPurchaseCost == null ? BigDecimal.ZERO : totalPurchaseCost);
        response.setTotalProfit(response.getTotalRevenue().subtract(response.getTotalPurchaseCost()));
        response.setGeneratedAt(LocalDateTime.now());
        return response;
    }
}
