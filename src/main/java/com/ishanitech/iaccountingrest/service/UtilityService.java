package com.ishanitech.iaccountingrest.service;


import java.util.List;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.TaxFileIrdDTO;

public interface UtilityService {

    TaxFileIrdDTO findTaxFileUtilitySummary(int compId, String fiscalYear, Integer quarter);

    List<PurchaseBillDTO> getLimitedPurchaseBillsForIrd(Integer offset, String fiscalYear, Integer quarter,
            Integer pageTotalItems, Integer compId, Integer branchId, String searchInput, String searchValue);
}
