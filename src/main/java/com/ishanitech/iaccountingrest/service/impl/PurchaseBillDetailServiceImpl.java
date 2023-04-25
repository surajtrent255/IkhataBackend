package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PurchaseBillDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailWithProdInfo;
import com.ishanitech.iaccountingrest.dto.PurchaseBillInvoice;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PurchaseBillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseBillDetailServiceImpl implements PurchaseBillDetailService {
    private final DbService dbService;

    @Override
    public PurchaseBillDetailDTO getSinglePurchaseInfo(int id) {
        return dbService.getDao(PurchaseBillDetailDAO.class).getSinglePurchaseInfoById(id);
    }

    @Override
    public PurchaseBillInvoice getPurchaseInfoByBillId(int billId, int companyId, int branchId) {
        PurchaseBillInvoice purchaseBillInvoiceDTO = new PurchaseBillInvoice();
        List<PurchaseBillDetailWithProdInfo> purchaseBillDetailWithProdInfoDTOS = dbService.getDao(PurchaseBillDetailDAO.class).getPurchaseInfoWithProdNameByBillId(billId, companyId, branchId);
        purchaseBillInvoiceDTO.setPurchaseBillDetailsWithProd(purchaseBillDetailWithProdInfoDTOS);
        purchaseBillInvoiceDTO.setPurchaseBillDTO(dbService.getDao(PurchaseBillDAO.class).getBillById(billId, companyId, branchId));
        return purchaseBillInvoiceDTO;
    }
}
