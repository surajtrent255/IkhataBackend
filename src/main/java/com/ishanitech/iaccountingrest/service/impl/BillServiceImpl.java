package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BillNoGeneratorDAO;
import com.ishanitech.iaccountingrest.dao.FiscalYearDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dao.StockDAO;
import com.ishanitech.iaccountingrest.dto.FiscalYearInfo;
import com.ishanitech.iaccountingrest.dto.PaginationTypeClass;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.service.BillService;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.utils.CustomQueryCreator;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    // @Override
    // public Flux<SalesBillDTO> getAllBills() {
    // return Flux.defer(() ->
    // Flux.fromIterable(dbService.getDao(SalesBillDAO.class).getAllBills()));
    // }

    @Override
    public SalesBillDTO getBillById(int id) {
        return dbService.getDao(SalesBillDAO.class).getBillById(id);
    }

    @Transactional
    @Override
    public void deleteBillById(int id) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        SalesBillDetailDAO salesBillDetailDAO = dbService.getDao(SalesBillDetailDAO.class);
        SalesBillDTO salesBillDTO = salesBillDAO.getBillById(id);
        List<SalesBillDetailDTO> salesBillDetailDTOS = salesBillDetailDAO.getSalesInfoByBillId(id);
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        salesBillDetailDTOS.forEach((salesBillDetailDTO -> {
            stockDAO.increaseStockQuantity(salesBillDetailDTO.getProductId(), salesBillDTO.getCompanyId(),
                    salesBillDTO.getBranchId(), salesBillDetailDTO.getQty());
        }));
        dbService.getDao(SalesBillDAO.class).deleteBillById(id);
    }

    @Override
    public Integer printTheBill(int billId, int userId) {
        Integer printSuccess = dbService.getDao(SalesBillDAO.class).printTheBillWithBillId(billId, new Date(), userId);
        SalesBillDTO salesBillDTO = dbService.getDao(SalesBillDAO.class).getBillById(billId);
        log.info("Sales Bill with id" + billId + " has been printed  by user with userId " + userId + " salesBillDTO"
                + salesBillDTO);
        return printSuccess;
    }

    @Override
    public List<SalesBillDTO> getAllBillsByCompId(int compId, int branchId) {
        List<SalesBillDTO> salesBillDTOList = dbService.getDao(SalesBillDAO.class).getSalesBillByCompanyId(compId,
                branchId);
        return salesBillDTOList;
    }

    @Override
    public List<SalesBillDTO> getLimitedSalesBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems,
            String searchBy, String searchWildCard, Integer compId, Integer branchId) {

        List<SalesBillDTO> salesBillList;
        String caseQuery = "";
        if (Objects.equals(searchBy, "pan")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and customer_pan = '"
                    + searchWildCard + "' order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "date")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and bill_date = CAST( '"
                    + searchWildCard + "' AS DATE)" + " order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "billNo")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and bill_no = '" + searchWildCard
                    + "' order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "customerOrSellerName")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and customer_name = '"
                    + searchWildCard + "' order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "month")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-"
                    + searchWildCard + "-%'" + " ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy, "fiscalYear")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " AND fiscal_year = '"
                    + searchWildCard + "'" + " ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy, "dateBetween")) {
            String[] dateArray = searchWildCard.split(" ");
            String startDate = dateArray[0];
            String endDate = dateArray[1];
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " AND bill_date BETWEEN CAST('"
                    + startDate + "' AS DATE) AND CAST('" + endDate + "' AS DATE) " + " ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy, "") || searchBy.length() == 0) {
            caseQuery = " company_id = " + compId + " and branch_id =" + branchId + " order by id " + " desc " +
                    "limit " + pageTotalItems + " offset " + (offset - 1);
        }
        salesBillList = dbService.getDao(SalesBillDAO.class).getLimitedSalesBillByCompanyAndBranchId(caseQuery);
        return salesBillList;
    }

    @Override
    public Double todayTotalSalesBillAmount(String todayDate, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        return salesBillDAO.todayTotalSalesBillAmount(todayDate, companyId, branchId);
    }

    @Override
    public Double ThisMonthTotalSalesBillAmount(String month, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String caseQuery = "company_id=" + companyId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-"
                + month + "-%'";
        return salesBillDAO.monthTotalSalesBillAmount(caseQuery);
    }

    @Override
    public Double fiscalYearTotalSalesBillAmount(String fiscalYear, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String caseQuery = "company_id=" + companyId + " and branch_id = " + branchId + " AND fiscal_year LIKE '%"
                + fiscalYear + "%'";
        return salesBillDAO.fiscalYearTotalSalesBillAmount(caseQuery);
    }

    @Override
    public Double todayTotalSalesBillTaxAmount(String todayDate, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        return salesBillDAO.todayTotalSalesBillTaxAmount(todayDate, companyId, branchId);
    }

    @Override
    public Double ThisMonthTotalSalesBillTaxAmount(String month, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String caseQuery = "company_id=" + companyId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-"
                + month + "-%'";
        return salesBillDAO.monthTotalSalesBillTaxAmount(caseQuery);
    }

    @Override
    public Double fiscalYearTotalSalesBillTaxAmount(String fiscalYear, int companyId, int branchId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        String caseQuery = "company_id=" + companyId + " and branch_id = " + branchId + " AND fiscal_year LIKE '%"
                + fiscalYear + "%'";
        return salesBillDAO.fiscalYearTotalSalesBillTaxAmount(caseQuery);
    }

    @Transactional
    @Override
    public Object approveTheBillById(int billId) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        SalesBillDTO salesBillDTO = salesBillDAO.getBillById(billId);
        List<SalesBillDetailDTO> salesBillDetailDTOS = dbService.getDao(SalesBillDetailDAO.class)
                .getSalesInfoByBillId(billId);
        StockDAO stockDAO = dbService.getDao(StockDAO.class);

        BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
        int bill_no = billNoGeneratorDAO.getBillNoForCurrentFiscalYear(salesBillDTO.getCompanyId(),
                salesBillDTO.getBranchId(), salesBillDTO.isHasAbbr());

        String billNoToBeUpdated = "B01 " + bill_no;
        salesBillDetailDTOS.forEach((salesBillDetailDTO -> {
            stockDAO.decreaseTheStockQuantity(salesBillDetailDTO.getProductId(), salesBillDTO.getCompanyId(),
                    salesBillDTO.getBranchId(), salesBillDetailDTO.getQty());
        }));
        salesBillDAO.makeDraftFalse(billNoToBeUpdated, billId);
        return salesBillDTO;
    }

    @Override
    public List<SalesBillDTO> getLimitedSalesBillsExcludingDraftByCompIdAndBranchId( String fiscalYear, Integer quarter, Integer offset,
            Integer pageTotalItems, String searchBy, String searchWildCard, Integer compId, Integer branchId) {
        // TODO Auto-generated method stub
        List<SalesBillDTO> salesBillList;
        FiscalYearDAO fiscalYearDAO = dbService.getDao(FiscalYearDAO.class);

        // extracting quarter
        FiscalYearInfo fiscalYearInfo = fiscalYearDAO.getFiscalYear(fiscalYear);
        LocalDateTime quarterStart = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime quarterEnd = LocalDateTime.of(2023, 1, 1, 0, 0);
        switch (quarter) {
            case 1:
                quarterStart = fiscalYearInfo.getFirstQuarterStart();
                quarterEnd = fiscalYearInfo.getFirstQuarterEnd();
                break;

            case 2:
                quarterStart = fiscalYearInfo.getSecondQuarterStart();
                quarterEnd = fiscalYearInfo.getSecondQuarterEnd();
                break;

            case 3:
                quarterStart = fiscalYearInfo.getThirdQuarterStart();
                quarterEnd = fiscalYearInfo.getThirdQuarterEnd();
                break;

            default:
                break;
        }
        // extractign end
        String caseQuery = "";
        if (Objects.equals(searchBy, "pan")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and customer_pan = '"
                    + searchWildCard + " and draft = false and is_bill_active = TRUE AND bill_date BETWEEN '"+quarterStart+"'' AND '" + quarterEnd   +"' order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "date")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and bill_date = CAST( '"
                    + searchWildCard + "' AS DATE)" + " and is_bill_active = TRUE AND bill_date BETWEEN "+quarterStart+" AND " + quarterEnd   +" order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "billNo")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and bill_no = '" + searchWildCard
                    + "' and is_bill_active = TRUE AND bill_date BETWEEN "+quarterStart+" AND " + quarterEnd   +" order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "customerOrSellerName")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " and customer_name = '"
                    + searchWildCard + "' and is_bill_active = TRUE AND bill_date BETWEEN "+quarterStart+" AND " + quarterEnd   +" order by bill_no desc " +
                    " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        if (Objects.equals(searchBy, "month")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-"
                    + searchWildCard + "-%'" + " and is_bill_active = TRUE AND bill_date BETWEEN "+quarterStart+" AND " + quarterEnd   +" ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy, "fiscalYear")) {
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " AND fiscal_year = '"
                    + searchWildCard + "'" + " and is_bill_active = TRUE AND bill_date BETWEEN "+quarterStart+" AND " + quarterEnd   +" ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy, "dateBetween")) {
            String[] dateArray = searchWildCard.split(" ");
            String startDate = dateArray[0];
            String endDate = dateArray[1];
            caseQuery = "company_id=" + compId + " and branch_id = " + branchId + " AND bill_date BETWEEN CAST('"
                    + startDate + "' AS DATE) AND CAST('" + endDate + "' AS DATE) " + " and is_bill_active = TRUE  ORDER BY bill_no DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchBy, "") || searchBy.length() == 0) {
            caseQuery = " company_id = " + compId + " and draft = false  and status=true  and fiscal_year='"+fiscalYear +  "' and is_bill_active = TRUE AND bill_date BETWEEN '"+quarterStart+"' AND '" + quarterEnd   +"' order by id " + " desc " 
                    + "limit " + pageTotalItems + " offset " + (offset - 1)
                    ;
        }
        if(fiscalYear.equalsIgnoreCase("all")){
           caseQuery =  caseQuery.replace("and fiscal_year='all'", " ");
            caseQuery = caseQuery.replace("And fiscal_year='all'", " ");
            caseQuery = caseQuery.replace(" and is_bill_active = TRUE ", " ");
        }
        salesBillList = dbService.getDao(SalesBillDAO.class).getLimitedSalesBillByCompanyAndBranchId(caseQuery);
        return salesBillList;
    }


    public List<SalesBillDTO> getAllDebtors(Integer companyId, Integer branchId){
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        List<SalesBillDTO> debtors = salesBillDAO.getAllDebtors(companyId, branchId);
        return debtors;
    }

    @Override
    public SalesBillDTO getDebtorsBillDetail(Integer id) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        SalesBillDTO sbd = salesBillDAO.fetchDebtorsBillDetail(id);
        return sbd;
    }

    @Override
    public List<SalesBillDTO> getAllDebtors(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String caseQuery = CustomQueryCreator.generateQueryWithCase(request, PaginationTypeClass.DEBTORS, dbService);
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        List<SalesBillDTO> salesBillDTOs = salesBillDAO.getLimitedDebtors(caseQuery);
        return salesBillDTOs;
        
    }
}