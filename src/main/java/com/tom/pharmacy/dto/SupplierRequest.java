package com.tom.pharmacy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {
    @NotBlank(message = "供应商名称不能为空")
    private String name;

    private String contactPerson;

    @NotBlank(message = "联系电话不能为空")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String address;
}
