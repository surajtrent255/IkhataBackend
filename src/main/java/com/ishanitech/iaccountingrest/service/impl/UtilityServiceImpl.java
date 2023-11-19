package com.ishanitech.iaccountingrest.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ishanitech.iaccountingrest.dao.FiscalYearDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dto.FiscalYearInfo;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.TaxFileIrdDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UtilityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UtilityServiceImpl implements UtilityService {
    private final DbService dbService;

    @Override
    public TaxFileIrdDTO findTaxFileUtilitySummary(int compId, String fiscalYear, Integer quarter) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        FiscalYearDAO fiscalYearDAO = dbService.getDao(FiscalYearDAO.class);
        LocalDateTime quarterStart = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime quarterEnd = LocalDateTime.of(2023, 1, 1, 0, 0);
        FiscalYearInfo fiscalYearInfo = fiscalYearDAO.getFiscalYear(fiscalYear);
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

        TaxFileIrdDTO tf = salesBillDAO.findTotalSalesAmountForCompany(compId, fiscalYear,
                // Date.from(quarterStart.atZone(ZoneId.systemDefault()).toInstant()),
                // Date.from(quarterEnd.atZone(ZoneId.systemDefault()).toInstant()));
                quarterStart, quarterEnd);



        return tf;
    }

    @Override
    public List<PurchaseBillDTO> getLimitedPurchaseBillsForIrd(Integer offset, String fiscalYear, Integer quarter,
            Integer pageTotalItems, Integer companyId, Integer branchId, String searchInput, String searchValue) {
        // TODO Auto-generated method stub
        List<PurchaseBillDTO> salesBillDTOList;
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
        String caseQuery = "";
        if (Objects.equals(searchInput,"pan") ){
            caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " and seller_pan = '" + searchValue + "' order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"date")){
            caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " and bill_date = CAST( '" + searchValue + "' AS DATE)" + " order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"billNo")){
            caseQuery = "company_id=" +companyId + " and branch_id = "+ branchId + "  and purchase_bill_no = '" + searchValue + "' order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"customerOrSellerName")){
            caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " and seller_name = '" + searchValue + "' order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if (Objects.equals(searchInput,"month")){
            caseQuery = "company_id = " + companyId + " AND branch_id = " + branchId + " AND transactional_date_nepali LIKE '%-" + searchValue + "-%'" + " ORDER BY bill_date DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchInput,"fiscalYear")){
            caseQuery = "company_id = " + companyId + " AND branch_id = " + branchId + " AND fiscal_year = '" + searchValue + "'" + " ORDER BY bill_date DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchInput,"dateBetween")){
            String[] dateArray = searchValue.split(" ");
            String startDate = dateArray[0];
            String endDate = dateArray[1];
            caseQuery = "company_id = " + companyId + " AND branch_id = " + branchId + " AND bill_date BETWEEN CAST('"+ startDate +"' AS DATE) AND CAST('"+ endDate +"' AS DATE) " + " ORDER BY bill_date DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if(Objects.equals(searchInput, " ") || searchInput.length() == 0 ){
            caseQuery = "company_id=" +companyId + " AND status = true  AND is_bill_active = TRUE AND fiscal_year = '"+fiscalYear+"'AND bill_date BETWEEN '"+  quarterStart+"' AND '" +quarterEnd+"' order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        salesBillDTOList = dbService.getDao(PurchaseBillDAO.class).getLimitedPurchaseBillByCompanyAndBranchId(caseQuery);
        return salesBillDTOList;    }
}
