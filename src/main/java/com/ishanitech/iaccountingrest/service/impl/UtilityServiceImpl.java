package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dto.TaxFileIrdDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UtilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilityServiceImpl implements UtilityService {
    private final DbService dbService;
    @Override
    public TaxFileIrdDTO findTaxFileUtilitySummary(int compId, String fiscalYear) {
        SalesBillDAO salesBillDAO = dbService.getDao(SalesBillDAO.class);
        return salesBillDAO.findTotalSalesAmountForCompany(compId, fiscalYear);
    }
}
