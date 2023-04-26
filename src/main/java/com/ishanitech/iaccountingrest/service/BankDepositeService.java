package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BankDepositeDTO;

import java.util.List;

public interface BankDepositeService  {
    List<BankDepositeDTO> getAllByBankBankDeposite(int companyId, int branchId);

    int addBankDeposit(BankDepositeDTO bankDepositeDTO);

    int updateDeposite(BankDepositeDTO bankDepositeDTO);

    int deleteFromBankDepositeBranchId(int branchId,String chequeNumber);


}
