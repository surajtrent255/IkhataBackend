package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;

import java.util.List;

public interface OtherIncomeSourceService {
    List<OtherIncomeSourceDTO> getAllOtherIncomeSources(Integer companyId, Integer branchId);

    Integer addNewOtherIncomeSource(OtherIncomeSourceDTO otherIncomeSource);
}
