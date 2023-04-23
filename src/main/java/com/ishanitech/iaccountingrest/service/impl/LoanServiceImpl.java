package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.LoanDAO;
import com.ishanitech.iaccountingrest.dto.LoanDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.LoanService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final DbService dbService;
    private LoanDAO loanDAO;

    @Override
    public Integer createNewLoan(LoanDTO loanDTO) {
        setLoanDAO();
        int newLoanId;
        newLoanId = this.loanDAO.createLoanEntity(loanDTO);
        return newLoanId;
    }

    @Override
    public List<LoanDTO> getAllLoanEntitiesForSingleCompAndBranch(int compId, int branchId) {
        setLoanDAO();
        List<LoanDTO> loanDTOS;
        loanDTOS = this.loanDAO.getAllLoanEntityByCompAndBranch(compId, branchId);
        return loanDTOS;
    }

    @Override
    public LoanDTO getSingleLoanEntity(int compId, int branchId, int id) {
        setLoanDAO();
        LoanDTO loanDTO;
        loanDTO = this.loanDAO.getSingleLoan(compId,branchId, id);
        return loanDTO;
    }

    @Override
    public void updateTheLoan(int compId, int branchId, int id, LoanDTO loanDTO) {
        setLoanDAO();
        this.loanDAO.updateLoanEntiy(loanDTO, id, compId, branchId);

    }

    @Override
    public void deleteTheLoanEntity(int compId, int branchId, int id ) {
        setLoanDAO();
        this.loanDAO.deleteLoanEntity(id, compId, branchId);

    }

    public void setLoanDAO(){
        this.loanDAO= dbService.getDao(LoanDAO.class);
    }
}
