package com.ishanitech.iaccountingrest.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseBillDetailDTO {
    private int id;
    private int productId;
    private int qty;
    private Date date;
    private double discountPerUnit;
    private double rate;
    private int billId;
    private int companyId;

}
