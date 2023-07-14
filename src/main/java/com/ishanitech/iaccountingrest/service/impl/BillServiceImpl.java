package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BillNoGeneratorDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dao.StockDAO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.service.BillService;
import com.ishanitech.iaccountingrest.service.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final DbService dbService;
    @Override
    public List<SalesBillDTO> getAllBills() {
        return dbService.getDao(SalesBillDAO.class).getAllBills();
    }

    @Override
    public SalesBillDTO getBillById(int id) {
        return dbService.getDao(SalesBillDAO.class).getBillById(id);
    }

    @Transactional
    @Override
    public void deleteBillById(int id) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        SalesBillDetailDAO salesBillDetailDAO = dbService.getDao(SalesBillDetailDAO.class);
        SalesBillDTO salesBillDTO =  salesBillDAO.getBillById(id);
        List<SalesBillDetailDTO> salesBillDetailDTOS= salesBillDetailDAO.getSalesInfoByBillId(id);
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        salesBillDetailDTOS.forEach((salesBillDetailDTO -> {
            stockDAO.increaseStockQuantity(salesBillDetailDTO.getProductId(), salesBillDTO.getCompanyId(), salesBillDTO.getBranchId(), salesBillDetailDTO.getQty());
        }));
         dbService.getDao(SalesBillDAO.class).deleteBillById(id);
    }

    @Override
    public Integer printTheBill(int billId, int userId) {
        Integer printSuccess = dbService.getDao(SalesBillDAO.class).printTheBillWithBillId(billId, new Date(), userId);
        SalesBillDTO salesBillDTO = dbService.getDao(SalesBillDAO.class).getBillById(billId);
        log.info("Sales Bill with id"+ billId + " has been printed  by user with userId "+userId+ " salesBillDTO"+ salesBillDTO);
       return printSuccess;
    }

    @Override
    public List<SalesBillDTO> getAllBillsByCompId(int compId, int branchId) {
        List<SalesBillDTO> salesBillDTOList = dbService.getDao(SalesBillDAO.class).getSalesBillByCompanyId(compId,  branchId);
        return salesBillDTOList;
    }

    @Override
    public List<SalesBillDTO> getLimitedSalesBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, String searchBy, String searchWildCard, Integer compId, Integer branchId) {

        List<SalesBillDTO> productList;
        String caseQuery="";
        if (Objects.equals(searchBy,"pan") ){
            caseQuery = "company_id=" +compId + " and branch_id = " + branchId + " and customer_pan = '"+  searchWildCard + "' order by bill_no desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchBy,"date")){
            caseQuery = "company_id=" +compId + " and branch_id = " + branchId + " and bill_date = CAST( '" + searchWildCard + "' AS DATE)" + " order by bill_no desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchBy,"billNo")){
            caseQuery  = "company_id=" +compId + " and branch_id = " + branchId + " and bill_no = '" + searchWildCard + "' order by bill_no desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchBy,"customerOrSellerName")){
            caseQuery  = "company_id=" +compId + " and branch_id = " + branchId + " and customer_name = '" + searchWildCard + "' order by bill_no desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if (Objects.equals(searchBy,"month")){
            caseQuery  = "company_id=" +compId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-" + searchWildCard + "-%'" + " ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy,"fiscalYear")){
            caseQuery  = "company_id=" +compId + " and branch_id = " + branchId + " AND fiscal_year = '" + searchWildCard + "'" + " ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy,"dateBetween")){
            String[] dateArray = searchWildCard.split(" ");
            String startDate = dateArray[0];
            String endDate = dateArray[1];
            caseQuery  = "company_id=" +compId + " and branch_id = " + branchId + " AND bill_date BETWEEN CAST('"+ startDate +"' AS DATE) AND CAST('"+ endDate +"' AS DATE) " + " ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if(Objects.equals(searchBy, "") || searchBy.length() == 0 ){
            caseQuery = " company_id = "+compId+" and branch_id ="+branchId+" order by bill_no "+" desc "+
                    "limit "+ pageTotalItems+" offset "+(offset-1);
        }
        productList = dbService.getDao(SalesBillDAO.class).getLimitedSalesBillByCompanyAndBranchId(caseQuery);
        return productList;
    }

    @Override
    public Double todayTotalSalesBillAmount(String todayDate,int companyId,int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        return salesBillDAO.todayTotalSalesBillAmount(todayDate,companyId,branchId);
    }

    @Override
    public Double ThisMonthTotalSalesBillAmount(String month, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String   caseQuery  = "company_id=" +companyId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-" + month + "-%'" ;
        return salesBillDAO.monthTotalSalesBillAmount(caseQuery);
    }

    @Override
    public Double fiscalYearTotalSalesBillAmount(String fiscalYear, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " AND fiscal_year LIKE '%" + fiscalYear + "%'";
        return salesBillDAO.fiscalYearTotalSalesBillAmount(caseQuery);
    }

    @Override
    public Double todayTotalSalesBillTaxAmount(String todayDate, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        return salesBillDAO.todayTotalSalesBillTaxAmount(todayDate,companyId,branchId);
    }

    @Override
    public Double ThisMonthTotalSalesBillTaxAmount(String month, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String   caseQuery  = "company_id=" +companyId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-" + month + "-%'" ;
        return salesBillDAO.monthTotalSalesBillTaxAmount(caseQuery);
    }

    @Override
    public Double fiscalYearTotalSalesBillTaxAmount(String fiscalYear, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " AND fiscal_year LIKE '%" + fiscalYear + "%'";
        return salesBillDAO.fiscalYearTotalSalesBillTaxAmount(caseQuery);
    }

    @Transactional
    @Override
    public Object approveTheBillById(int billId) {
        SalesBillDAO salesBillDAO =  dbService.getDao(SalesBillDAO.class);
        SalesBillDTO salesBillDTO = salesBillDAO.getBillById(billId);
        List<SalesBillDetailDTO> salesBillDetailDTOS = dbService.getDao(SalesBillDetailDAO.class).getSalesInfoByBillId(billId);
        StockDAO stockDAO = dbService.getDao(StockDAO.class);

        BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
        int bill_no = billNoGeneratorDAO.getBillNoForCurrentFiscalYear(salesBillDTO.getCompanyId(), salesBillDTO.getBranchId(), salesBillDTO.isHasAbbr());

        String billNoToBeUpdated = "B01 "+bill_no;
        salesBillDetailDTOS.forEach((salesBillDetailDTO -> {
            stockDAO.decreaseTheStockQuantity(salesBillDetailDTO.getProductId(), salesBillDTO.getCompanyId(), salesBillDTO.getBranchId(), salesBillDetailDTO.getQty());
        }));
        salesBillDAO.makeDraftFalse(billNoToBeUpdated, billId);
        return salesBillDTO;
    }

}