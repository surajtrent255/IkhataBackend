package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BankDepositDTO;


import java.util.List;

public interface BankDepositeService  {
    List<BankDepositDTO> getAllByBankBankDeposite(int companyId, int branchId);

    int addBankDeposit(BankDepositDTO bankDepositeDTO);

    int updateDeposite(BankDepositDTO bankDepositeDTO);

    int deleteFromBankDepositeBranchId(int branchId,int depositId);

    List<BankDepositDTO> getLimitedBankDepositByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);

    BankDepositDTO getSingleBankDeposit(Integer id, Integer companyId, Integer branchId);
}
