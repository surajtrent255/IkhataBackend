package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillInvoice;

public interface PurchaseBillDetailService {
    PurchaseBillDetailDTO getSinglePurchaseInfo(int id);

    PurchaseBillInvoice getPurchaseInfoByBillId(int billId, int companyId, int branchId);
}
