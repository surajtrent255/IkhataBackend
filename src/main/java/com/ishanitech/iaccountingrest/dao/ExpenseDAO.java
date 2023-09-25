package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.ExpenseDTO;
import com.ishanitech.iaccountingrest.dto.ExpenseTopicDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ExpenseDAO {
    @SqlUpdate("INSERT INTO expenses( " +
            " company_id, amount, topic, bill_no, pay_to, date,nepali_date, branch_id) " +
            " VALUES (:companyId, :amount, :topic, :billNo, :payTo, :date,:nepaliDate, :branchId );")
    Integer addExpenseDetails(@BindBean ExpenseDTO expenseDTO);


    @SqlQuery("SELECT * FROM expenses WHERE company_id=:companyId")
    @RegisterBeanMapper(ExpenseDTO.class)
    List<ExpenseDTO> getExpenseDetails(@Bind int companyId);

    @SqlQuery("SELECT * FROM expenses WHERE sn=:SN")
    @RegisterBeanMapper(ExpenseDTO.class)
    ExpenseDTO getExpenseDetailsBySN(@Bind int SN);

    @SqlUpdate("UPDATE public.expenses " +
            " SET   amount=:amount, topic=:topic, bill_no=:billNo, pay_to=:payTo, date=:date,nepali_date=:nepaliDate " +
            " WHERE sn=:SN;")
    void updateExpenseDetails(@BindBean ExpenseDTO expenseDTO);

    @SqlUpdate("DELETE FROM expenses WHERE sn= :SN")
    @Nullable
    void deleteFromExpense(@Bind int SN);


    @SqlQuery("""
            select * from expenses ex where ex.company_id = :compId and ex.branch_id = :branchId order by SN desc limit :pageTotalItems offset (:offset -1) ;
            """)
    @RegisterBeanMapper(ExpenseDTO.class)
    List<ExpenseDTO> getLimitedExpensesByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);


    @SqlQuery("""
            select * from expense_topics where company_id = :compId and branch_id = :branchId
            """)
    @RegisterBeanMapper(ExpenseTopicDTO.class)
    List<ExpenseTopicDTO> getAllExpenseTopicsByCompanyANdBranch(Integer compId, Integer branchId);


    @SqlUpdate("""
            insert into expense_topics (name, company_id, branch_id) VALUES ( :name,  :companyId, :branchId)
            """)
    Integer createNewExpenseTopicByCompanyAndBranch(@BindBean ExpenseTopicDTO expenseTopicDTO);
}
