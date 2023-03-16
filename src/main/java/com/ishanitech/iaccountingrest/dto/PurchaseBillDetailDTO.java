package com.ishanitech.iaccountingrest.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseBillDetailDTO {
    private int id;
    private Date date;
    private int productId;
    private double rate;
    private int qty;
    private int purchaseBillId;
    private int companyId;
    private double discount;

}
