package com.tom.pharmacy.service;

import com.tom.pharmacy.dto.DrugRequest;
import com.tom.pharmacy.dto.DrugResponse;
import com.tom.pharmacy.entity.Drug;
import com.tom.pharmacy.entity.Supplier;
import com.tom.pharmacy.exception.ResourceNotFoundException;
import com.tom.pharmacy.repository.DrugRepository;
import com.tom.pharmacy.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrugService {
    private final DrugRepository drugRepository;
    private final SupplierRepository supplierRepository;

    public List<DrugResponse> findAll() {
        return drugRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public DrugResponse findById(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("药品不存在，ID=" + id));
        return toResponse(drug);
    }

    @Transactional
    public DrugResponse create(DrugRequest request) {
        drugRepository.findByDrugCode(request.getDrugCode())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("药品编码已存在: " + existing.getDrugCode());
                });

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("供应商不存在，ID=" + request.getSupplierId()));

        Drug drug = new Drug();
        drug.setDrugCode(request.getDrugCode());
        drug.setName(request.getName());
        drug.setGenericName(request.getGenericName());
        drug.setSpecification(request.getSpecification());
        drug.setCategory(request.getCategory());
        drug.setDosageForm(request.getDosageForm());
        drug.setSupplier(supplier);
        drug.setPurchasePrice(request.getPurchasePrice());
        drug.setSellingPrice(request.getSellingPrice());
        drug.setCurrentStock(request.getCurrentStock());
        drug.setSafetyStock(request.getSafetyStock());
        drug.setActive(true);

        return toResponse(drugRepository.save(drug));
    }

    @Transactional
    public DrugResponse update(Long id, DrugRequest request) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("药品不存在，ID=" + id));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("供应商不存在，ID=" + request.getSupplierId()));

        drugRepository.findByDrugCode(request.getDrugCode())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new IllegalArgumentException("药品编码已存在: " + request.getDrugCode());
                    }
                });

        drug.setDrugCode(request.getDrugCode());
        drug.setName(request.getName());
        drug.setGenericName(request.getGenericName());
        drug.setSpecification(request.getSpecification());
        drug.setCategory(request.getCategory());
        drug.setDosageForm(request.getDosageForm());
        drug.setSupplier(supplier);
        drug.setPurchasePrice(request.getPurchasePrice());
        drug.setSellingPrice(request.getSellingPrice());
        drug.setSafetyStock(request.getSafetyStock());
        drug.setActive(true);

        if (request.getCurrentStock() >= 0) {
            drug.setCurrentStock(request.getCurrentStock());
        }

        return toResponse(drugRepository.save(drug));
    }

    @Transactional
    public void delete(Long id) {
        Drug drug = drugRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("药品不存在，ID=" + id));
        drug.setActive(false);
        drugRepository.save(drug);
    }

    private DrugResponse toResponse(Drug drug) {
        DrugResponse response = new DrugResponse();
        response.setId(drug.getId());
        response.setDrugCode(drug.getDrugCode());
        response.setName(drug.getName());
        response.setGenericName(drug.getGenericName());
        response.setSpecification(drug.getSpecification());
        response.setCategory(drug.getCategory());
        response.setDosageForm(drug.getDosageForm());
        if (drug.getSupplier() != null) {
            response.setSupplierId(drug.getSupplier().getId());
            response.setSupplierName(drug.getSupplier().getName());
        }
        response.setPurchasePrice(drug.getPurchasePrice());
        response.setSellingPrice(drug.getSellingPrice());
        response.setCurrentStock(drug.getCurrentStock());
        response.setSafetyStock(drug.getSafetyStock());
        response.setActive(drug.getActive());
        response.setCreatedAt(drug.getCreatedAt());
        response.setUpdatedAt(drug.getUpdatedAt());
        return response;
    }
}
