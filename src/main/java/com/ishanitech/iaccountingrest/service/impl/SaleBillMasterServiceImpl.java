package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BillNoGeneratorDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dao.StockDAO;
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
        SalesBillDTO  salesBillDTO= saleBillMasterDTO.getSalesBillDTO();
        List<SalesBillDetailDTO> salesBillDetailDTOS = saleBillMasterDTO.getSalesBillDetails();

        int bill_no = 0;
        String currentFiscalYear = null;
        try{
            BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
            bill_no = billNoGeneratorDAO.getBillNoForCurrentFiscalYear();
            currentFiscalYear = dbService.getDao(BillNoGeneratorDAO.class).getCurrentFiscalYear();
        } catch (Exception ex){
            log.error("genating billno() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while generating  bill no");
        }
        salesBillDTO.setBillNo("B01"+bill_no);
        salesBillDTO.setFiscalYear(currentFiscalYear);


        int billId = 0 ;
        try{
            SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
              billId = salesBillDAO.addNewBill(
                     salesBillDTO);

        } catch (Exception ex){
            log.error("addNewSaleBill() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding bill");
        }

        for(SalesBillDetailDTO salesBillDetailDTO: salesBillDetailDTOS ){
            salesBillDetailDTO.setBillId(billId);
            salesBillDetailDTO.setCompanyId(salesBillDTO.getCompanyId());
            salesBillDetailDTO.setBranchId(salesBillDTO.getBranchId());
            salesBillDetailDTO.setDate(salesBillDTO.getBillDate());

        }
//        for updating stock qty count

        try{
            StockDAO stockDAO = dbService.getDao(StockDAO.class);
            salesBillDetailDTOS.forEach((salesBillDetailDTO -> {
                stockDAO.decreaseTheStockQuantity(salesBillDetailDTO.getProductId(), salesBillDTO.getCompanyId(), salesBillDTO.getBranchId(), salesBillDetailDTO.getQty());
            }));

        } catch (Exception ex){
            log.error("updateStockQty() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while updating stock qty ");
        }

        try{
            SalesBillDetailDAO salesBillDetailDAO = dbService.getDao(SalesBillDetailDAO.class);
            salesBillDetailDAO.addNewSalesInfo(salesBillDetailDTOS);
        } catch (Exception ex){
            log.error("addNewSalesInfo() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding billdetail");
        }

        return new ResponseDTO<Integer>(billId);
    }
}
