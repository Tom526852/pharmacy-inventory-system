package com.tom.pharmacy.controller;

import com.tom.pharmacy.dto.InventoryMovementRequest;
import com.tom.pharmacy.entity.Drug;
import com.tom.pharmacy.entity.InventoryMovement;
import com.tom.pharmacy.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/movement")
    public ResponseEntity<InventoryMovement> recordMovement(@Valid @RequestBody InventoryMovementRequest request) {
        return ResponseEntity.ok(inventoryService.recordMovement(request));
    }

    @PostMapping("/inbound")
    public ResponseEntity<InventoryMovement> inbound(@Valid @RequestBody InventoryMovementRequest request) {
        request.setMovementType(com.tom.pharmacy.entity.InventoryMovementType.INBOUND);
        return ResponseEntity.ok(inventoryService.recordMovement(request));
    }

    @PostMapping("/outbound")
    public ResponseEntity<InventoryMovement> outbound(@Valid @RequestBody InventoryMovementRequest request) {
        request.setMovementType(com.tom.pharmacy.entity.InventoryMovementType.OUTBOUND);
        return ResponseEntity.ok(inventoryService.recordMovement(request));
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<Drug>> lowStockAlerts() {
        return ResponseEntity.ok(inventoryService.getLowStockDrugs());
    }

    @GetMapping("/records")
    public ResponseEntity<List<InventoryMovement>> latestRecords() {
        return ResponseEntity.ok(inventoryService.getLatestRecords());
    }
}
