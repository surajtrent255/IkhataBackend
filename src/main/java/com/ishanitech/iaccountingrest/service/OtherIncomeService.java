
package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.OtherIncomeDTO;
import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;

import java.util.List;

public interface OtherIncomeService {
    List<OtherIncomeDTO> getAllOtherIncomes(Integer companyId, Integer branchId);

    OtherIncomeDTO getOtherIncomeById(Integer id, Integer companyId, Integer branchId);

    Integer insertNewOtherIncome(OtherIncomeDTO otherIncome);

    void updateGivenOtherIncome(OtherIncomeDTO otherIncomeDTO);

    void deleteOtherIncomeById(Integer id, Integer companyId, Integer branchId);

    List<OtherIncomeSourceDTO> getAllOtherIncomeTypes();
}