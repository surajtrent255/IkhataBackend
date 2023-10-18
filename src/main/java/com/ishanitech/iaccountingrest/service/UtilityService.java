package com.ishanitech.iaccountingrest.service;


import com.ishanitech.iaccountingrest.dto.TaxFileIrdDTO;

public interface UtilityService {

    TaxFileIrdDTO findTaxFileUtilitySummary(int compId, String fiscalYear, Integer quarter);
}
