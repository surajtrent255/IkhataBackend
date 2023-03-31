package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillInvoiceDTO;

public interface SalesBillDetailService {

    SalesBillDetailDTO getSingleSalesInfo(int id);

    SalesBillInvoiceDTO getSalesInfoByBillId(int billId, int companyId);
}
