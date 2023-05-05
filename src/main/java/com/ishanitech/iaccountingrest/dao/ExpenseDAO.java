package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.ExpenseDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ExpenseDAO {
    @SqlUpdate("INSERT INTO expenses( " +
            " company_id, amount, topic, bill_no, pay_to, date, branch_id) " +
            " VALUES (:companyId, :amount, :topic, :billNo, :payTo, :date, :branchId );")
    Integer addExpenseDetails(@BindBean ExpenseDTO expenseDTO);


    @SqlQuery("SELECT * FROM expenses WHERE company_id=:companyId")
    @RegisterBeanMapper(ExpenseDTO.class)
    List<ExpenseDTO> getExpenseDetails(@Bind int companyId);

    @SqlQuery("SELECT * FROM expenses WHERE sn=:SN")
    @RegisterBeanMapper(ExpenseDTO.class)
    ExpenseDTO getExpenseDetailsBySN(@Bind int SN);

    @SqlUpdate("UPDATE public.expenses " +
            " SET   amount=:amount, topic=:topic, bill_no=:billNo, pay_to=:payTo, date=:date " +
            " WHERE sn=:SN;")
    void updateExpenseDetails(@BindBean ExpenseDTO expenseDTO);

    @SqlUpdate("DELETE FROM expenses WHERE sn= :SN")
    @Nullable
    void deleteFromExpense(@Bind int SN);

}
