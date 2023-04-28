package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.LoanDAO;
import com.ishanitech.iaccountingrest.dto.LoanDTO;
import com.ishanitech.iaccountingrest.dto.LoanNamesDTO;
import com.ishanitech.iaccountingrest.dto.LoanTypesDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final DbService dbService;

    @Override
    public Integer createNewLoan(LoanDTO loanDTO) {
        LoanDAO loanDAO = dbService.getDao(LoanDAO.class);;
        int newLoanId;
        newLoanId = loanDAO.createLoanEntity(loanDTO);
        return newLoanId;
    }

    @Override
    public List<LoanDTO> getAllLoanEntitiesForSingleCompAndBranch(int compId, int branchId) {
        LoanDAO loanDAO = dbService.getDao(LoanDAO.class);;
        List<LoanDTO> loanDTOS;
        loanDTOS = loanDAO.getAllLoanEntityByCompAndBranch(compId, branchId);
        return loanDTOS;
    }

    @Override
    public LoanDTO getSingleLoanEntity(int compId, int branchId, int id) {
        LoanDAO loanDAO = dbService.getDao(LoanDAO.class);;
        LoanDTO loanDTO;
        loanDTO = loanDAO.getSingleLoan(id, compId,branchId);
        return loanDTO;
    }

    @Override
    public void updateTheLoan(int compId, int branchId, int id, LoanDTO loanDTO) {
        LoanDAO loanDAO = dbService.getDao(LoanDAO.class);;
        loanDAO.updateLoanEntiy(loanDTO, id, compId, branchId);

    }

    @Override
    public void deleteTheLoanEntity(int compId, int branchId, int id ) {
        LoanDAO loanDAO = dbService.getDao(LoanDAO.class);;
        loanDAO.deleteLoanEntity(id, compId, branchId);

    }

    @Override
    public List<LoanTypesDTO> getAllLoanTypes() {
        LoanDAO loanDAO = dbService.getDao(LoanDAO.class);;
        List<LoanTypesDTO> loanTypesDTOS;
        loanTypesDTOS = loanDAO.getAllLoanTypes();
        return loanTypesDTOS;
    }

    @Override
    public List<LoanNamesDTO> getAllLoanNames() {
        LoanDAO loanDAO = dbService.getDao(LoanDAO.class);;
        List<LoanNamesDTO> loanNamesDTOS;
        loanNamesDTOS = loanDAO.getAllLoanNames();
        return loanNamesDTOS;
    }
}
