package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.AccountTypeDTO;
import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.BankListDTO;
import com.ishanitech.iaccountingrest.dto.TypePaymentDTO;

import java.util.List;

public interface BankService {



    List<BankDTO> getAllByBankId(int id);

    List<BankDTO> getAllByBankName(String bankName);

    List<BankDTO> getAllByBank();




    List<BankDTO> getAllByBankCompany(int companyId);

    List <BankDTO> getAllByBankBranch(int companyId, int branchId);

    int addbank(BankDTO bankDTO);

    int updateBank(BankDTO bankDTO);

    void deleteBankByCompanyAndBranchId(int companyId,int branchId);

    int DeleteFromBankByAccountNo(int bankId);

    List<BankListDTO> getAllBankList();

    List<TypePaymentDTO> getAllPayment();

    List<AccountTypeDTO> getAccountType();

    List<BankDTO> getLimitedBanksByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
