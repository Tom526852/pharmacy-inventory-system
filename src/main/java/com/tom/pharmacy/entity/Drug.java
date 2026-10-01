package com.tom.pharmacy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "drugs")
@Getter
@Setter
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String drugCode;

    @Column(nullable = false)
    private String name;

    @Column(name = "generic_name")
    private String genericName;

    @Column(nullable = false)
    private String specification;

    @Column(nullable = false)
    private String category;

    @Column(name = "dosage_form")
    private String dosageForm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(name = "purchase_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal purchasePrice;

    @Column(name = "selling_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal sellingPrice;

    @Column(name = "current_stock", nullable = false)
    private Integer currentStock;

    @Column(name = "safety_stock", nullable = false)
    private Integer safetyStock;

    @Column(nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
