package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherIncomeDTO {
    private Integer sn;
    private Integer source;
    private Double amount;
    private Date dateEnglish;
    private String dateNepali;
    private Integer companyId;
    private Integer branchId;
}
