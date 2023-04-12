package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.VatRateTypesDTO;

import java.util.List;

public interface VatRateService {
    List<VatRateTypesDTO> getAllVatRateTypes();
}
