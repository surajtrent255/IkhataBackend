package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseAdditionalInfoDTO {

    private int expenseId;

    private Long supplierPan;

    private String supplierName;

    private String invoiceDate;

    private String invoiceNo;

    private double amount;

    private boolean vatBill;

    private int companyId;

    private int branchId;

    private String billNo;

    private int attributeId;

    private String attributeName;


}
