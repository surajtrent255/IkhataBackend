package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.ExpenseDAO;
import com.ishanitech.iaccountingrest.dto.ExpenseDTO;
import com.ishanitech.iaccountingrest.dto.ExpenseTopicDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private DbService dbService;
    @Override
    public Integer addExpenseDetails(ExpenseDTO expenseDTO) {
        ExpenseDAO expenseDAO = dbService.getDao(ExpenseDAO.class);
        int done = expenseDAO.addExpenseDetails(expenseDTO);
        return done;
    }

    @Override
    public List<ExpenseDTO> getExpenseDetails(int companyId) {
        ExpenseDAO expenseDAO = dbService.getDao(ExpenseDAO.class);
        return expenseDAO.getExpenseDetails(companyId);
    }

    @Override
    public ExpenseDTO getExpenseDetailsBySN(int SN) {
        ExpenseDAO expenseDAO = dbService.getDao(ExpenseDAO.class);
        return expenseDAO.getExpenseDetailsBySN(SN);
    }

    @Override
    public void updateExpenseDetails(ExpenseDTO expenseDTO) {
        ExpenseDAO expenseDAO = dbService.getDao(ExpenseDAO.class);
        expenseDAO.updateExpenseDetails(expenseDTO);
    }

    @Override
    public void deleteFromExpense(int SN) {
        ExpenseDAO expenseDAO = dbService.getDao(ExpenseDAO.class);
        expenseDAO.deleteFromExpense(SN);

    }

    @Override
    public List<ExpenseDTO> getLimitedExpenseDetails(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId) {
        List<ExpenseDTO> expenses;
        expenses = dbService.getDao(ExpenseDAO.class).getLimitedExpensesByCompanyAndBranchId(offset, pageTotalItems, compId, branchId);
        return expenses;
    }

    @Override
    public Integer createTopicDTO(ExpenseTopicDTO expenseTopicDTO) {
        ExpenseDAO expenseDAO = dbService.getDao(ExpenseDAO.class);
        Integer expenseTopicId = 0;
        expenseTopicId = expenseDAO.createNewExpenseTopicByCompanyAndBranch(expenseTopicDTO);
        return expenseTopicId;
    }

    @Override
    public List<ExpenseTopicDTO> getAllExpenseTopicsByCompIdAndBranchId(Integer compId, Integer branchId) {
        ExpenseDAO expenseDAO = dbService.getDao(ExpenseDAO.class);
        List<ExpenseTopicDTO> expensesList = new ArrayList<>();
//        ExpenseTopicDTO e1  = new ExpenseTopicDTO();
//        e1.setId(1);
//        e1.setName("hero");
//        expensesList.add(e1);
        expensesList = expenseDAO.getAllExpenseTopicsByCompanyANdBranch(compId, branchId);

        return expensesList;

    }
}
