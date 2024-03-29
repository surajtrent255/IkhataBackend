package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseReportDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.jdbi.v3.sqlobject.customizer.Define;

import java.util.List;

public interface PurchaseBillService {
    List<PurchaseBillDTO> getAllPurchaseBills();

    PurchaseBillDTO getSinglePurchasBillInfo(Integer id);

    void deletePurchaseBill(int id);

    List<PurchaseBillDTO> getAllPurchaseBillsByCompanyId(int compId, int branchId);

    List<PurchaseBillDTO> getLimitedPurchaseBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer companyId, Integer branchId,String searchInput, String searchValue);

    PurchaseReportDTO getPurchaseBillInfoForReport(Integer id, Integer compId, Integer branchId);

    Double todayTotalPurchaseBillAmount(String todayDate,int companyId,int branchId);

    Double ThisMonthTotalPurchaseBillAmount(String month,int companyId,int branchId);

    Double fiscalYearTotalPurchaseBillAmount(String fiscalYear,int companyId,int branchId);

    Double todayTotalPurchaseBillTaxAmount(String todayDate,int companyId,int branchId);

    Double ThisMonthTotalPurchaseBillTaxAmount(String month,int companyId,int branchId);

    Double fiscalYearTotalPurchaseBillTaxAmount(String fiscalYear,int companyId,int branchId);

    List<PurchaseBillDTO> getAllCreditors(HttpServletRequest request);

    List<PurchaseBillDTO> getPurchaseBillForCreditorDetailPage(int companyId, int branchId, String sellerPan, String searchInput,Integer offset, Integer pageTotalItems);

}
