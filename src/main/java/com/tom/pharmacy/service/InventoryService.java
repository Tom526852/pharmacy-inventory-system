package com.tom.pharmacy.service;

import com.tom.pharmacy.dto.InventoryMovementRequest;
import com.tom.pharmacy.entity.Drug;
import com.tom.pharmacy.entity.InventoryMovement;
import com.tom.pharmacy.entity.InventoryMovementType;
import com.tom.pharmacy.entity.Supplier;
import com.tom.pharmacy.exception.ResourceNotFoundException;
import com.tom.pharmacy.repository.DrugRepository;
import com.tom.pharmacy.repository.InventoryMovementRepository;
import com.tom.pharmacy.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final DrugRepository drugRepository;
    private final SupplierRepository supplierRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    @Transactional
    public InventoryMovement recordMovement(InventoryMovementRequest request) {
        Drug drug = drugRepository.findById(request.getDrugId())
                .orElseThrow(() -> new ResourceNotFoundException("药品不存在，ID=" + request.getDrugId()));

        Supplier supplier = null;
        if (request.getSupplierId() != null) {
            supplier = supplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("供应商不存在，ID=" + request.getSupplierId()));
        }

        if (request.getMovementType() == null) {
            throw new IllegalArgumentException("出入库类型不能为空");
        }

        InventoryMovement movement = new InventoryMovement();
        movement.setDrug(drug);
        movement.setSupplier(supplier != null ? supplier : drug.getSupplier());
        movement.setMovementType(request.getMovementType());
        movement.setQuantity(request.getQuantity());
        movement.setUnitPrice(request.getUnitPrice() == null ? drug.getPurchasePrice() : request.getUnitPrice());
        movement.setOrderNo(request.getOrderNo());
        movement.setRemark(request.getRemark());

        if (request.getTotalAmount() != null) {
            movement.setTotalAmount(request.getTotalAmount());
        } else {
            movement.setTotalAmount(movement.getUnitPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        }

        switch (request.getMovementType()) {
            case INBOUND -> {
                drug.setCurrentStock(drug.getCurrentStock() + request.getQuantity());
                if (request.getSupplierId() != null) {
                    drug.setSupplier(supplier);
                }
            }
            case OUTBOUND, SALE -> {
                if (drug.getCurrentStock() < request.getQuantity()) {
                    throw new IllegalArgumentException("库存不足，当前库存：" + drug.getCurrentStock() + "，出库数量：" + request.getQuantity());
                }
                drug.setCurrentStock(drug.getCurrentStock() - request.getQuantity());
            }
            case ADJUSTMENT -> {
                drug.setCurrentStock(request.getQuantity());
            }
            default -> throw new IllegalArgumentException("不支持的库存类型：" + request.getMovementType());
        }

        drugRepository.save(drug);
        return inventoryMovementRepository.save(movement);
    }

    public List<InventoryMovement> getLatestRecords() {
        return inventoryMovementRepository.findAll().stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .limit(20)
                .toList();
    }

    public List<Drug> getLowStockDrugs() {
        return drugRepository.findAll().stream()
                .filter(drug -> drug.getCurrentStock() <= drug.getSafetyStock())
                .toList();
    }
}
