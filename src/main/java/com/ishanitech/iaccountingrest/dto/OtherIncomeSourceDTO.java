package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherIncomeSourceDTO {
    private Integer id;
    private String name;
    private Integer companyId;
    private Integer branchId;
}
