package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.*;
import com.ishanitech.iaccountingrest.dto.*;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.SaleBillMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleBillMasterServiceImpl implements SaleBillMasterService {
    private final DbService dbService;

    @Transactional
    @Override
    public ResponseDTO<Integer> addNewSaleBill(SaleBillMasterDTO saleBillMasterDTO) {
        int alreadyDraft = saleBillMasterDTO.getAlreadyDraft();
        if(alreadyDraft > 0){
            dbService.getDao(SalesBillDAO.class).permanentBillDeleteById(alreadyDraft);//detail ko pani delete garna xa
        }
        SalesBillDTO  salesBillDTO= saleBillMasterDTO.getSalesBillDTO();
        List<SalesBillDetailDTO> salesBillDetailDTOS = saleBillMasterDTO.getSalesBillDetails();

        int bill_no = 0;
        String currentFiscalYear = null;
        try{
            if(!salesBillDTO.isDraft()){
                BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
                bill_no = billNoGeneratorDAO.getBillNoForCurrentFiscalYear(salesBillDTO.getCompanyId(), salesBillDTO.getBranchId(), salesBillDTO.isHasAbbr());
            }
            currentFiscalYear = dbService.getDao(BillNoGeneratorDAO.class).getCurrentFiscalYear();
        } catch (Exception ex){
            log.error("genating billno() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while generating  bill no");
        }

        String waiter = currentFiscalYear;
        StringBuilder sb = new StringBuilder(waiter);
        int[] indicesToRemove ={6,5,4,1,0};
        for (int j : indicesToRemove) {
            sb.deleteCharAt(j);
        }

        String billNumberAdvanced = "B01-"+ sb.toString()+"-"+bill_no;
        salesBillDTO.setBillNo(billNumberAdvanced);
        salesBillDTO.setFiscalYear(currentFiscalYear);


        int billId = 0 ;
        try{
            SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
            salesBillDTO.setPrintCount(0);
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
            salesBillDetailDTO.setDate(salesBillDTO.getBillDateNepali());

        }
//        for updating stock qty count
    if(!salesBillDTO.isDraft()){
        try{
            StockDAO stockDAO = dbService.getDao(StockDAO.class);
            salesBillDetailDTOS.forEach((salesBillDetailDTO -> {
                stockDAO.decreaseTheStockQuantity(salesBillDetailDTO.getProductId(), salesBillDTO.getCompanyId(), salesBillDTO.getBranchId(), salesBillDetailDTO.getQty());
            }));

        } catch (Exception ex){
            log.error("updateStockQty() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while updating stock qty ");
        }

    }

        try{
            SalesBillDetailDAO salesBillDetailDAO = dbService.getDao(SalesBillDetailDAO.class);
            salesBillDetailDAO.addNewSalesInfo(salesBillDetailDTOS);
        } catch (Exception ex){
            log.error("addNewSalesInfo() ========> "+ex.getMessage());
            throw new CustomSqlException("something went wrong while adding billdetail");
        }

//        for salesReceiptGeneraton
        if(salesBillDTO.isReceipt()){
            SalesReceiptDTO salesReceiptDTO = new SalesReceiptDTO();
            salesReceiptDTO.setReceiptDate(LocalDate.now());
            salesReceiptDTO.setHasAbbr(salesBillDTO.isHasAbbr());
            salesReceiptDTO.setBillNo(billNumberAdvanced);
            salesReceiptDTO.setReceiptAmount(salesBillDTO.getTotalAmount());
            salesReceiptDTO.setCompanyId(salesBillDTO.getCompanyId());
            salesReceiptDTO.setBranchId(salesBillDTO.getBranchId());

            int receiptNo;

            try{
                BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
                receiptNo = billNoGeneratorDAO.getSalesReceiptNoForCurrentFiscalYear(salesBillDTO.getCompanyId(), salesBillDTO.getBranchId(), salesBillDTO.isHasAbbr());
                salesReceiptDTO.setReceiptNo(receiptNo);
                billNoGeneratorDAO.createNewSalesReceipt(salesReceiptDTO);
            } catch (Exception ex){
                log.error("sales_receipt no generation () ======> " + ex.getMessage());
            }
        }


        return new ResponseDTO<Integer>(billId);
    }



    public void changeFiscalYear(String fiscalYear){

//        also including customers;
//        default branch 0 branch table ma basdaina;
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        List<CompanyDTO> companies  = companyDAO.getAllCompanyList();
        List<BranchDTO> branches = branchDAO.getAllCompanyAndBranch();
        List<BillNoGenerationDTO> billNoGenerationDTOs = new ArrayList<>();


        branches.forEach(b -> {
            BillNoGenerationDTO billNoGenerationDTO = BillNoGenerationDTO.builder()
                    .companyId(b.getCompanyId())
                    .branchId(b.getId())
                    .active(true)
                    .hasAbbr(false)
                    .fiscalYear(fiscalYear).build();
            billNoGenerationDTOs.add(billNoGenerationDTO);

            BillNoGenerationDTO billNoGenerationDTO2 = BillNoGenerationDTO.builder()
                    .companyId(b.getCompanyId())
                    .active(true)
                    .hasAbbr(true)
                    .branchId(b.getId()) .fiscalYear(fiscalYear).build();
            billNoGenerationDTOs.add(billNoGenerationDTO2);
        });

        companies.forEach(ci->{
            BillNoGenerationDTO billNoGenerationDTO = BillNoGenerationDTO.builder().companyId(ci.getCompanyId()).branchId(0).active(true).hasAbbr(false) .fiscalYear(fiscalYear).build();
            billNoGenerationDTOs.add(billNoGenerationDTO);

            BillNoGenerationDTO billNoGenerationDTO2= BillNoGenerationDTO.builder().companyId(ci.getCompanyId()).branchId(0).active(true).hasAbbr(true) .fiscalYear(fiscalYear).build();
            billNoGenerationDTOs.add(billNoGenerationDTO2);
        });

        BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
//        billNoGeneratorDAO.createNewFiscalYear(billNoGenerationDTOs)
        System.out.println("dsfsdf");
        billNoGeneratorDAO.initilizeBillNoForNewFiscalYear(billNoGenerationDTOs);
    }
}
