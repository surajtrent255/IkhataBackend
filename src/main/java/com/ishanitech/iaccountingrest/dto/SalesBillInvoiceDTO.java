package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesBillInvoiceDTO {
    private List<SalesBillDetailWithProdInfoDTO> salesBillDetailsWithProd;
    private SalesBillDTO salesBillDTO;
    private CompanyDTO sellerCompany;
    private String customerAddress;

}

