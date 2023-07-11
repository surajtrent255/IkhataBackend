package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dao.DepositWithDrawTypesDAO;
import com.ishanitech.iaccountingrest.dto.DepositWithdrawTypesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepositWithDrawTypeServiceImpl  implements  DepositWithDrawTypeService{

    private final DbService dbService;
    @Override
    public List<DepositWithdrawTypesDTO> getAllTypesOfDepositWithdraw() {
//        int a = 6/0;
        DepositWithDrawTypesDAO depositWithDrawTypesDAO = dbService.getDao(DepositWithDrawTypesDAO.class);
        List<DepositWithdrawTypesDTO> depositWithdrawTypesDTOS;
        depositWithdrawTypesDTOS = depositWithDrawTypesDAO.getAllDepositWithDrawTypes();
        return depositWithdrawTypesDTOS;
    }
}
