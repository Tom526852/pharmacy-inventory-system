package com.tom.pharmacy.controller;

import com.tom.pharmacy.dto.DrugRequest;
import com.tom.pharmacy.dto.DrugResponse;
import com.tom.pharmacy.service.DrugService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
public class DrugController {
    private final DrugService drugService;

    @GetMapping
    public ResponseEntity<List<DrugResponse>> list() {
        return ResponseEntity.ok(drugService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugResponse> detail(@PathVariable Long id) {
        return ResponseEntity.ok(drugService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DrugResponse> create(@Valid @RequestBody DrugRequest request) {
        return ResponseEntity.ok(drugService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrugResponse> update(@PathVariable Long id, @Valid @RequestBody DrugRequest request) {
        return ResponseEntity.ok(drugService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        drugService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
