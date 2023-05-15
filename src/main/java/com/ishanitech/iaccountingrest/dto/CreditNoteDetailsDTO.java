package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditNoteDetailsDTO {
    private Long serialNumber;

    private  int productId;

    private String productName;

    private String creditReason;

    private Double creditAmount;

    private Double creditTaxAmount;

    private int companyId;

    private int branchId;

    private String billNumber;
}
