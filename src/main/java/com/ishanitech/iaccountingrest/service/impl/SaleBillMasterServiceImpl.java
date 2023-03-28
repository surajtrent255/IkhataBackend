package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.SaleBillMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleBillMasterServiceImpl implements SaleBillMasterService {
    private final DbService dbService;

    @Transactional
    @Override
    public ResponseDTO<Integer> addNewSaleBill(SaleBillMasterDTO saleBillMasterDTO) {
        SalesBillDTO salesBillDTO = new SalesBillDTO();
        salesBillDTO.setUserId(saleBillMasterDTO.getUserId());
        salesBillDTO.setCustId(saleBillMasterDTO.getCustId());
        salesBillDTO.setCompanyId(saleBillMasterDTO.getCompanyId());
        salesBillDTO.setStatus(saleBillMasterDTO.isStatus());
        salesBillDTO.setInvoiceNo(saleBillMasterDTO.getInvoiceNo());
        salesBillDTO.setFiscalYear(saleBillMasterDTO.getFiscalYear());
        salesBillDTO.setDiscount(saleBillMasterDTO.getDiscount());
        salesBillDTO.setTotal(saleBillMasterDTO.getTotal());
        salesBillDTO.setTax(saleBillMasterDTO.getTax());
        salesBillDTO.setGrandTotal(saleBillMasterDTO.getGrandTotal());
//
        SalesBillDetailDTO salesBillDetailDTO = new SalesBillDetailDTO();
        salesBillDetailDTO.setProductId(saleBillMasterDTO.getProductId());
        salesBillDetailDTO.setQty(saleBillMasterDTO.getQty());
        salesBillDetailDTO.setDiscountPerUnit(saleBillMasterDTO.getDiscountPerUnit());
        salesBillDetailDTO.setRate(saleBillMasterDTO.getRate());
        salesBillDetailDTO.setCompanyId(saleBillMasterDTO.getCompanyId());
        int billId = 0;
        try{
            SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
             billId = salesBillDAO.addNewBill(salesBillDTO);

        } catch (Exception ex){
            log.error("addNewSaleBill() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding bill");
        }
        salesBillDetailDTO.setBillId(billId);

        try{
            SalesBillDetailDAO salesBillDetailDAO = dbService.getDao(SalesBillDetailDAO.class);
            salesBillDetailDAO.addNewSalesInfo(salesBillDetailDTO);
        } catch (Exception ex){
            log.error("addNewSalesInfo() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding billdetail");
        }

        return new ResponseDTO<Integer>(billId);
    }
}
