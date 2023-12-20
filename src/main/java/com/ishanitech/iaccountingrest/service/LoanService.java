package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.LoanDTO;
import com.ishanitech.iaccountingrest.dto.LoanNamesDTO;
import com.ishanitech.iaccountingrest.dto.LoanTypesDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;

import java.util.List;
import java.util.Map;

public interface LoanService {
    Integer createNewLoan(LoanDTO loanDTO);

    List<LoanDTO> getAllLoanEntitiesForSingleCompAndBranch(int compId, int branchId);

    LoanDTO getSingleLoanEntity(int compId, int branchId, int id);

    void updateTheLoan(int compId, int branchId, int id, LoanDTO loanDTO);

    void deleteTheLoanEntity(int compId, int branchId, int id);

    List<LoanTypesDTO> getAllLoanTypes();

    List<LoanNamesDTO> getAllLoanNames();

    List<LoanDTO> getLimitedLoanEntitiesForSingleCompAndBranch(Integer offset, Integer pageTotalItems,String searchBy, String searchWildCard, String sortBy, Integer compId, Integer branchId);

    Map<String, String> getLoanNameForLoanRepay( int Id,  int companyId,  int branchId);

}
