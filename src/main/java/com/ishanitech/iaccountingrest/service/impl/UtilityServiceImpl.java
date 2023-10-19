package com.ishanitech.iaccountingrest.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ishanitech.iaccountingrest.dao.FiscalYearDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dto.FiscalYearInfo;
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

            case 4:
                quarterStart = fiscalYearInfo.getFourthQuarterStart();
                quarterEnd = fiscalYearInfo.getFourthQuarterEnd();
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
}
