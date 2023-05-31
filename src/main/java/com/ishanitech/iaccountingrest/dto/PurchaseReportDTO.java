package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseReportDTO {

    private PurchaseBillDTO purchaseBill;
    private List<PurchaseBillDetailWithProdInfo> purchaseBillDetailWithProdInfos;
}
