package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.LoanDTO;
import com.ishanitech.iaccountingrest.dto.LoanRepayDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface LoanRepayDAO {


    @SqlUpdate("""
            INSERT INTO loan_repay (
                loan_no,
                loan_name,
                amount,
                interest,
                penalty,
                service,
                principle,
                nepali_date,
                company_id,
                branch_id
            ) VALUES (
                :loanNo,
                :loanName,
                :amount,
                :interest,
                :penalty,
                :service,
                :principle,
                :nepaliDate,
                :companyId,
                :branchId
            );            
            """)
    @RegisterBeanMapper(LoanRepayDTO.class)
    void addLoanRepay(@BindBean LoanRepayDTO loanRepayDTO);

    @SqlQuery("""
            SELECT * FROM loan_repay WHERE company_id = :companyId AND branch_id =:branchId;
              """)
    @RegisterBeanMapper(LoanRepayDTO.class)
    List<LoanRepayDTO> getLoanRepayDetails(@Bind int companyId,@Bind int branchId);

    @SqlQuery("""
            SELECT * FROM loan_repay WHERE company_id = :companyId AND branch_id =:branchId AND id= :Id;
              """)
    @RegisterBeanMapper(LoanRepayDTO.class)
    LoanRepayDTO getSingleLoanRepayDetails(@Bind int companyId,@Bind int branchId,@Bind int Id);

    @SqlUpdate("""
            UPDATE loan_repay
            SET loan_no = :loanNo,
                loan_name = :loanName,
                interest = :interest,
                penalty = :penalty,
                service = :service,
                principle = :principle,
                amount = :amount
            WHERE company_id = :companyId
              AND branch_id = :branchId  
              AND id = :Id ;         
            """)
    @RegisterBeanMapper(LoanRepayDTO.class)
    void updateLoanRepay(@BindBean LoanRepayDTO loanRepayDTO);


    @SqlUpdate("""
            DELETE FROM loan_repay WHERE id=:Id
            """)
    void deleteLoanRepayEntry(@Bind int Id);



}
