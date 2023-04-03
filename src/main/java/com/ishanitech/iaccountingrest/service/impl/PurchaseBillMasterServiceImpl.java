package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.*;
import com.ishanitech.iaccountingrest.dto.*;
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


    @Transactional
    @Override
    public ResponseDTO<Integer> addNewPurchaseBill(PurchaseBillMasterDTO purchaseBillMasterDTO) {

        PurchaseBillDTO purchaseBillDTO = purchaseBillMasterDTO.getPurchaseBillDTO();
        List<PurchaseBillDetailDTO> purchaseBillDetailDTOS = purchaseBillMasterDTO.getPurchaseBillDetails();
        String currentFiscalYear = null;
        try{
            currentFiscalYear = dbService.getDao(BillNoGeneratorDAO.class).getCurrentFiscalYear();
        } catch (Exception ex){
            log.error("genating billno() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while generating  bill no");
        }
        purchaseBillDTO.setFiscalYear(currentFiscalYear);
        for(PurchaseBillDetailDTO purchaseBillDetailDTO: purchaseBillDetailDTOS){
            purchaseBillDetailDTO.setPurchaseBillId(purchaseBillDTO.getPurchaseBillNo());
            purchaseBillDetailDTO.setCompanyId(purchaseBillDTO.getCompanyId());
        }

        try{
            PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
            purchaseBillDAO.addNewPurchaseBill(purchaseBillDTO);
        } catch (Exception ex){
            log.error("addPurchaseBill() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding purchase bill");
        }

//        for updating stock qty count

        try{
            StockDAO stockDAO = dbService.getDao(StockDAO.class);
            purchaseBillDetailDTOS.forEach((purchaseBillDetailDTO -> {
                stockDAO.increaseStockQuantityWithAttrs(purchaseBillDetailDTO.getProductId(), purchaseBillDetailDTO.getCompanyId(), purchaseBillDetailDTO.getQty());
            }));

        } catch (Exception ex){
            log.error("updateStockQty() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while updating stock qty ");
        }

        try{
            PurchaseBillDetailDAO purchaseBillDetailDAO = dbService.getDao(PurchaseBillDetailDAO.class);
            purchaseBillDetailDAO.addNewPurchaseInfo(purchaseBillDetailDTOS);
        } catch (Exception ex){
            log.error("addNewPurchaseInfo() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding purchase detail");
        }

        return new ResponseDTO<Integer>(purchaseBillDTO.getPurchaseBillNo());
    }


}
