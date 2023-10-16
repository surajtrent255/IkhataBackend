package com.ishanitech.iaccountingrest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxFileIrdDTO {

    private long totalSale;
    private long totalVatOnSale;
    private long totalSalesBill;

    private long totalPurchase;
    private long totalVatOnPurchase;
    private long totalPurchaseBill;


}
