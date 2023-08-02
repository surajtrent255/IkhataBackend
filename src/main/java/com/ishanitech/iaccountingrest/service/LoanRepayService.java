package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.LoanRepayDTO;

import java.util.List;

public interface LoanRepayService {
    void addLoanRepayDetails(LoanRepayDTO loanRepayDTO);

    List<LoanRepayDTO> getLoanRepayDetails(int companyId, int branchId);

    LoanRepayDTO getSingleLoanRepayDetails(int companyId, int branchId,int id);


    void updateLoanRepay(LoanRepayDTO loanRepayDTO);

    void deleteLoanRepay(int id);
}
