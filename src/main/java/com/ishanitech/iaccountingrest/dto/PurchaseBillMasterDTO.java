package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseBillMasterDTO {
    private List<PurchaseBillDetailDTO> purchaseBillDetails;
    private PurchaseBillDTO purchaseBillDTO;
}
