package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PurchaseBillDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PurchaseBillMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseBillMasterServiceImpl implements PurchaseBillMasterService {
    private final DbService dbService;

    @Override
    public List<PurchaseBillMasterDTO> getAllPurchaseBills() {
        return null;
    }

    @Override
    public PurchaseBillMasterDTO getSinglePurchaseBill() {
        return null;
    }

    @Transactional
    @Override
    public ResponseDTO<Integer> addNewPurchaseBill(PurchaseBillMasterDTO purchaseBillMasterDTO) {
        PurchaseBillDTO purchaseBillDTO = new PurchaseBillDTO();
        purchaseBillDTO.setUserId(purchaseBillMasterDTO.getUserId());
        purchaseBillDTO.setCustId(purchaseBillMasterDTO.getCustId());
        purchaseBillDTO.setCompanyId(purchaseBillMasterDTO.getCompanyId());
        purchaseBillDTO.setStatus(purchaseBillMasterDTO.isStatus());
        purchaseBillDTO.setInvoiceNo(purchaseBillMasterDTO.getInvoiceNo());
        purchaseBillDTO.setFiscalYear(purchaseBillMasterDTO.getFiscalYear());
        purchaseBillDTO.setDiscount(purchaseBillMasterDTO.getDiscount());
        purchaseBillDTO.setTotal(purchaseBillMasterDTO.getTotal());
        purchaseBillDTO.setTax(purchaseBillMasterDTO.getTax());
        purchaseBillDTO.setGrandTotal(purchaseBillMasterDTO.getGrandTotal());
//
        PurchaseBillDetailDTO purchaseBillDetailDTO = new PurchaseBillDetailDTO();
        purchaseBillDetailDTO.setProductId(purchaseBillMasterDTO.getProductId());
        purchaseBillDetailDTO.setQty(purchaseBillMasterDTO.getQty());
        purchaseBillDetailDTO.setDiscountPerUnit(purchaseBillMasterDTO.getDiscountPerUnit());
        purchaseBillDetailDTO.setRate(purchaseBillMasterDTO.getRate());
        purchaseBillDetailDTO.setCompanyId(purchaseBillMasterDTO.getCompanyId());
        int billId = 0;
        try{
            PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
            billId = purchaseBillDAO.addNewPurchaseBill(purchaseBillDTO);

        } catch (Exception ex){
            log.error("addNewPurchase bill () ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding bill");
        }
        purchaseBillDetailDTO.setBillId(billId);

        try{
            PurchaseBillDetailDAO purchaseBillDetailDAO = dbService.getDao(PurchaseBillDetailDAO.class);
            purchaseBillDetailDAO.addNewPurchaseInfo(purchaseBillDetailDTO);
        } catch (Exception ex){
            log.error("addNew Purchase Info() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding billdetail");
        }

        return new ResponseDTO<Integer>(billId);
    }

    @Override
    public void deleteBill(int id) {

    }
}
