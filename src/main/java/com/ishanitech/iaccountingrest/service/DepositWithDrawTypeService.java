package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.DepositWithdrawTypesDTO;

import java.util.List;

public interface DepositWithDrawTypeService {

    List<DepositWithdrawTypesDTO> getAllTypesOfDepositWithdraw();
}
