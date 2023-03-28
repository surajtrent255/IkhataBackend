package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillInvoiceDTO;

import java.util.List;

public interface SalesBillDetailService {

    SalesBillDetailDTO getSingleSalesInfo(int id);

    SaleBillMasterDTO getSalesInfoByBillId(int billId, int companyId);
}
