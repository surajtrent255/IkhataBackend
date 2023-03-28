package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseBillMasterDTO {
    private Date date;
    private int userId;
    private int custId;
    private int companyId;
    private boolean status;
    private int productId;
    private int qty;
    private double discountPerUnit;
    private double rate;
    private int billId;
    private String invoiceNo;
    private String fiscalYear;
    private double discount;
    private double total;
    private double tax;
    private double grandTotal;
}
