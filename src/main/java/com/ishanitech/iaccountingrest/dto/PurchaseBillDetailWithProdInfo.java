package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseBillDetailWithProdInfo {
    private int id;
    private int productId;
    private int qty;
    private Date date;
    private double discountPerUnit;
    private double rate;
    private int purchaseBillId;
    private int companyId;
    private int branchId;
    private String productName;

    private String debitReason;
}
