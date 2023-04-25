package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private Integer id;
    private Integer companyId;
    private Integer branchId;
    private Integer bankId;
    private Integer lenderId;
    private Integer loanType;
    private Long loanNumber;
    private Integer loanName;
    private Double loanAmount;
    private Double receivedAmount;
    private Double serviceCharge;
    private Double otherExpenses;
}
