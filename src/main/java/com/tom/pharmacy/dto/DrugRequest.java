package com.tom.pharmacy.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugRequest {
    @NotBlank(message = "药品编码不能为空")
    private String drugCode;

    @NotBlank(message = "药品名称不能为空")
    private String name;

    private String genericName;

    @NotBlank(message = "规格不能为空")
    private String specification;

    @NotBlank(message = "分类不能为空")
    private String category;

    private String dosageForm;

    @NotNull(message = "供应商不能为空")
    private Long supplierId;

    @NotNull(message = "采购价不能为空")
    @DecimalMin(value = "0.00", inclusive = true, message = "采购价必须大于等于0")
    private BigDecimal purchasePrice;

    @NotNull(message = "销售价不能为空")
    @DecimalMin(value = "0.00", inclusive = true, message = "销售价必须大于等于0")
    private BigDecimal sellingPrice;

    @NotNull(message = "当前库存不能为空")
    @Min(value = 0, message = "当前库存不能小于0")
    private Integer currentStock;

    @NotNull(message = "安全库存不能为空")
    @Min(value = 0, message = "安全库存不能小于0")
    private Integer safetyStock;
}
