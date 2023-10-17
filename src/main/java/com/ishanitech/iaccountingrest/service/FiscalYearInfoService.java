package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.FiscalYearInfo;

public interface FiscalYearInfoService {

    int createNewFiscalYearInfo(FiscalYearInfo fiscalYearInfo);

    FiscalYearInfo getActiveFiscalYearInfo();
}
