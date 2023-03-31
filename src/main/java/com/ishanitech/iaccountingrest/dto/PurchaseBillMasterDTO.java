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
    private PurchaseBillDTO purchaseBillDTO;
    private PurchaseBillDetailDTO purchaseBillDetailDTO;
}
