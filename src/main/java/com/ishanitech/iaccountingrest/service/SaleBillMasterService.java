package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;

import java.util.List;

public interface SaleBillMasterService {

    ResponseDTO<Integer> addNewSaleBill(SaleBillMasterDTO saleBillMasterDTO);
    void changeFiscalYear(String fiscalYear);

}
