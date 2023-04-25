package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseBillInvoice {
    private PurchaseBillDTO purchaseBillDTO;
    private List<PurchaseBillDetailWithProdInfo> purchaseBillDetailsWithProd;

}
