package com.ishanitech.iaccountingrest.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenseTopicDTO {
    private Integer id;
    private String name;
    private Integer companyId;
    private Integer branchId;

}
