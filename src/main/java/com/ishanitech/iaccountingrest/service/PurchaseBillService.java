package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseReportDTO;

import java.util.List;

public interface PurchaseBillService {
    List<PurchaseBillDTO> getAllPurchaseBills();

    PurchaseBillDTO getSinglePurchasBillInfo(Integer id);

    void deletePurchaseBill(int id);

    List<PurchaseBillDTO> getAllPurchaseBillsByCompanyId(int compId, int branchId);

    List<PurchaseBillDTO> getLimitedPurchaseBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);

    PurchaseReportDTO getPurchaseBillInfoForReport(Integer id, Integer compId, Integer branchId);
}
