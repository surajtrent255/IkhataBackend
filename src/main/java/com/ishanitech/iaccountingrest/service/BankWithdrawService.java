package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;

import java.util.List;

public interface BankWithdrawService {
    List<BankWithdrawDTO> getAllWithdraw(int companyId, int branchId);
    int  addwithdraw(BankWithdrawDTO bankWithdrawDTO);
}
