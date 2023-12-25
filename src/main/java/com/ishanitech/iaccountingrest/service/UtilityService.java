package com.ishanitech.iaccountingrest.service;

import java.util.List;
import java.util.Map;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.TaxFileIrdDTO;

public interface UtilityService {

    TaxFileIrdDTO findTaxFileUtilitySummary(int compId, String fiscalYear, String qrtStart, String qrtEnd);

    List<PurchaseBillDTO> getLimitedPurchaseBillsForIrd(Integer offset, String fiscalYear, Integer quarter,
            Integer pageTotalItems, Integer compId, Integer branchId, String searchInput, String searchValue);

    TaxFileIrdDTO findTaxFileUtilitySummaryByMonth(Integer compId, String monthBegDate, String monthEndDate, String fiscalYear);

    void sendEmail(Map<?,?> object, String params);
}
