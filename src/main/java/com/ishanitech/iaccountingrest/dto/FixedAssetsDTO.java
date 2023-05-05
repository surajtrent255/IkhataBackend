package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FixedAssetsDTO {
    private int SN;
    private int companyId;
    private String name;

    private Double amount;

    private Date date;

    private int billNo;

    private Double cash;

    private String loan;

    private int loanId;

    private int branchId;

    private boolean status;
}
