package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;

import java.util.List;

public interface BankWithdrawService {
    List<BankWithdrawDTO> getAllWithdraw(int companyId, int branchId);
    int  addwithdraw(BankWithdrawDTO bankWithdrawDTO);

    int updatewithdraw(BankWithdrawDTO bankWithdrawDTO);

    int deletewithdraw(int branchId, int withdrawId);

    List<BankWithdrawDTO> getLimitedBanksWithdrawByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);

    BankWithdrawDTO getSingleBankWithdraw(Integer id, Integer companyId, Integer branchId);
}
