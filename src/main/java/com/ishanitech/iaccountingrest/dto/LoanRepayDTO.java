package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRepayDTO {
    private int Id;
    private int loanNo;
    private String loanName;

    private Double amount;
    private boolean interest;
    private boolean penalty;
    private boolean service;
    private boolean principle;
    private Date date;
    private String nepaliDate;
    private  int companyId;
    private int branchId;
}


