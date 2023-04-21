package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDTO {

    private int id;
    private int companyId;
    private int bankId;
    private int lenderId;
    private int loanType;
    private long loanNumber;
    private int loanName;
    private double loanAmount;
    private double receivedAmount;
    private double serviceCharge;
    private double otherExpenses;
}
