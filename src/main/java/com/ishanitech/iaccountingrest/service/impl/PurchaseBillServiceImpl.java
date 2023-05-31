package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.ProductDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.*;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PurchaseBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseBillServiceImpl implements PurchaseBillService {
    private final DbService dbService;
    @Override
    public List<PurchaseBillDTO> getAllPurchaseBills() {
        return dbService.getDao(PurchaseBillDAO.class).getAllPurchaseBills();
    }

    @Override
    public PurchaseBillDTO getSinglePurchasBillInfo(Integer id) {
        return dbService.getDao(PurchaseBillDAO.class).getSinglePurchaseBill(id);
    }


    @Override
    public void deletePurchaseBill(int id) {
        dbService.getDao(PurchaseBillDAO.class).deletePurchaseBill(id);
    }

    @Override
    public List<PurchaseBillDTO> getAllPurchaseBillsByCompanyId(int compId, int branchId) {
        return dbService.getDao(PurchaseBillDAO.class).getPurchaseBillByCompanyId(compId, branchId);
    }

    @Override
    public List<PurchaseBillDTO> getLimitedPurchaseBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId) {
        List<PurchaseBillDTO> salesBillDTOList;
        salesBillDTOList = dbService.getDao(PurchaseBillDAO.class).getLimitedPurchaseBillByCompanyAndBranchId(offset, pageTotalItems, compId, branchId);
        return salesBillDTOList;
    }

    @Override
    public PurchaseReportDTO getPurchaseBillInfoForReport(Integer id, Integer compId, Integer branchId) {
        PurchaseReportDTO purchaseReportDTO = new PurchaseReportDTO();
        PurchaseBillDTO purchaseBillDTO = dbService.getDao(PurchaseBillDAO.class).getSinglePurchaseBill(id);
        List<PurchaseBillDetailWithProdInfo> purchaseBillDetailsWithProd = dbService.getDao(PurchaseBillDetailDAO.class).getPurchaseInfoWithProdNameByBillId(id, compId, branchId);
        purchaseReportDTO.setPurchaseBill(purchaseBillDTO);
        purchaseReportDTO.setPurchaseBillDetailWithProdInfos(purchaseBillDetailsWithProd);
        return purchaseReportDTO;
    }
}
