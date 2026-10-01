package com.tom.pharmacy.dto;

import com.tom.pharmacy.entity.InventoryMovementType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMovementRequest {
    @NotNull(message = "药品ID不能为空")
    private Long drugId;

    private Long supplierId;

    @NotNull(message = "出入库类型不能为空")
    private InventoryMovementType movementType;

    @NotNull(message = "数量不能为空")
    @Positive(message = "数量必须大于0")
    private Integer quantity;

    @DecimalMin(value = "0.00", inclusive = true, message = "单价必须大于等于0")
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.00", inclusive = true, message = "金额必须大于等于0")
    private BigDecimal totalAmount;

    private String orderNo;

    @NotBlank(message = "备注不能为空")
    private String remark;
}
