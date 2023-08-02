package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.LoanRepayDAO;
import com.ishanitech.iaccountingrest.dto.LoanRepayDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.LoanRepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanRepayServiceImpl implements LoanRepayService {

    @Autowired
    private  DbService dbService;

    @Override
    public void addLoanRepayDetails(LoanRepayDTO loanRepayDTO) {
        LoanRepayDAO loanRepayDAO = dbService.getDao(LoanRepayDAO.class);
        loanRepayDAO.addLoanRepay(loanRepayDTO);
    }

    @Override
    public List<LoanRepayDTO> getLoanRepayDetails(int companyId, int branchId) {
        LoanRepayDAO loanRepayDAO = dbService.getDao(LoanRepayDAO.class);
        return loanRepayDAO.getLoanRepayDetails(companyId,branchId);
    }

    @Override
    public LoanRepayDTO getSingleLoanRepayDetails(int companyId, int branchId, int id) {
        LoanRepayDAO loanRepayDAO = dbService.getDao(LoanRepayDAO.class);
        return loanRepayDAO.getSingleLoanRepayDetails(companyId,branchId,id);
    }

    @Override
    public void updateLoanRepay(LoanRepayDTO loanRepayDTO) {
        LoanRepayDAO loanRepayDAO = dbService.getDao(LoanRepayDAO.class);
        loanRepayDAO.updateLoanRepay(loanRepayDTO);
    }

    @Override
    public void deleteLoanRepay(int id) {
        LoanRepayDAO loanRepayDAO = dbService.getDao(LoanRepayDAO.class);
        loanRepayDAO.deleteLoanRepayEntry(id);
    }
}
