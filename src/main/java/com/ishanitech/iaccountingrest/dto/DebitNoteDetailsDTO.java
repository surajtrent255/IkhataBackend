package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitNoteDetailsDTO {

    private int serialNumber;

    private  int productId;

    private String productName;

    private String productUnit;

    private String debitReason;

    private Double debitAmount;

    private Double totalDebitAmount;

    private Double debitTaxAmount;

    private int  companyId;

    private int branchId;

    private int billNumber;

}
