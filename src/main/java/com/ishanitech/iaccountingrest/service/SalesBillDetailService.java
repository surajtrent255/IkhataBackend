package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillInvoiceDTO;

import java.util.List;

public interface SalesBillDetailService {

    SalesBillDetailDTO getSingleSalesInfo(int id);

    SalesBillInvoiceDTO getSalesInfoByBillId(int billId);

    SalesBillInvoiceDTO getSalesInfoByBillNo(String billNo);



    List<SalesBillDetailDTO> getSaleBillDetailByBillId(int billId);
}
