package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.VatRateTypesDAO;
import com.ishanitech.iaccountingrest.dto.VatRateTypesDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.VatRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VatRateServiceImpl implements VatRateService {

    private final DbService dbService;
    @Override
    public List<VatRateTypesDTO> getAllVatRateTypes() {
        VatRateTypesDAO vatRateTypesDAO = dbService.getDao(VatRateTypesDAO.class);
        List<VatRateTypesDTO> vatRateTypesDTOS = vatRateTypesDAO.getAllVatRateTypes();
        return vatRateTypesDTOS;
    }
}
