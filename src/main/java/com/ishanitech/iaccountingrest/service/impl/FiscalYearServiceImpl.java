package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.FiscalYearDAO;
import com.ishanitech.iaccountingrest.dto.FiscalYearInfo;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.FiscalYearInfoService;
import com.ishanitech.iaccountingrest.service.SaleBillMasterService;
import com.ishanitech.iaccountingrest.service.SalesBillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class FiscalYearServiceImpl implements FiscalYearInfoService {

    private final DbService dbService;
    private final SaleBillMasterService saleBillMasterService;

    @Override
    public int createNewFiscalYearInfo(FiscalYearInfo fiscalYearInfo) {
        FiscalYearDAO fiscalYearDAO = dbService.getDao(FiscalYearDAO.class);
        int createdId = fiscalYearDAO.createNewFiscalYear(fiscalYearInfo);
//        saleBillMasterService.changeFiscalYear(fiscalYearInfo.getFiscalYear());
        return createdId;
    }

    @Override
    public FiscalYearInfo getActiveFiscalYearInfo() {
        FiscalYearDAO fiscalYearDAO = dbService.getDao(FiscalYearDAO.class);
        return fiscalYearDAO.getCurrentFiscalYearInfo();
    }
}
