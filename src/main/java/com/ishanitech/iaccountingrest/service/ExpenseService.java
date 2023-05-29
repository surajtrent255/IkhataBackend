package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {

    Integer addExpenseDetails(ExpenseDTO expenseDTO);

    List<ExpenseDTO> getExpenseDetails(int companyId);

    ExpenseDTO getExpenseDetailsBySN(int SN);

    void updateExpenseDetails(ExpenseDTO expenseDTO);

    void deleteFromExpense(int SN);

    List<ExpenseDTO> getLimitedExpenseDetails(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
