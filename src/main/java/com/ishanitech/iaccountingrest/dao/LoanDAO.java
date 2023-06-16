package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.LoanDTO;
import com.ishanitech.iaccountingrest.dto.LoanNamesDTO;
import com.ishanitech.iaccountingrest.dto.LoanTypesDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface LoanDAO {


    @GetGeneratedKeys
    @SqlUpdate("""
            INSERT INTO public.loan(
            	 company_id, branch_id, bank_id, lender_id, loan_type, loan_number, loan_name, loan_amount, received_amount, service_charge, other_expenses)
            	VALUES (:companyId, :branchId, :bankId, :lenderId, :loanType, :loanNumber, :loanName, :loanAmount, :receivedAmount, :serviceCharge, :otherExpenses);
            """)
    int createLoanEntity(@BindBean LoanDTO loanDTO);


    @SqlQuery("""
            SELECT l.id as id, l.company_id as company_id, l.branch_id as branch_id, l.bank_id as bank_id, l.lender_id as lender_id, l.loan_type as loan_type, l.loan_number as loan_number, l.loan_name as loan_name,
            l.loan_amount as loan_amount, l.received_amount as received_amount, l.service_charge as service_charge, l.other_expenses as other_expenses
            	FROM loan l  where l.company_id= :compId and l.branch_id = :branchId and l.deleted=false;
            """)
    @RegisterBeanMapper(LoanDTO.class)
    List<LoanDTO> getAllLoanEntityByCompAndBranch(int compId, int branchId);

    @SqlQuery("""
            SELECT l.id as id, l.company_id as company_id, l.branch_id as branch_id, l.bank_id as bank_id, l.lender_id as lender_id, l.loan_type as loan_type, l.loan_number as loan_number, l.loan_name as loan_name,
            l.loan_amount as loan_amount, l.received_amount as received_amount, l.service_charge as service_charge, l.other_expenses as other_expenses
            	FROM public.loan l  where l.company_id= :compId and l.branch_id = :branchId and l.id = :id and l.deleted=false;
            """)
    @RegisterBeanMapper(LoanDTO.class)
    LoanDTO getSingleLoan(int id, int compId, int branchId);

    @SqlUpdate("""
            UPDATE public.loan l
            	SET bank_id= :bankId, lender_id= :lenderId, loan_type= :loanType, loan_number= :loanNumber, loan_name= :loanName,
            	loan_amount= :loanAmount, received_amount= :receivedAmount, service_charge= :serviceCharge, other_expenses= :otherExpenses
            	WHERE  l.company_id= :compId and l.branch_id = :branchId and l.id = :id;
            """)
    void updateLoanEntiy(@BindBean LoanDTO loanDTO, int id, int compId, int branchId);



    @SqlUpdate("""
            UPDATE public.loan l
            	SET deleted = true
            	WHERE l.company_id= :compId and l.branch_id = :branchId and l.id = :id;
            """)
    void deleteLoanEntity(int id, int compId, int branchId);


    @SqlQuery("""
            select lt.id as id, lt.loan_type_index as loan_type_index, lt.loan_type as loan_type from loan_type lt
            """)
    @RegisterBeanMapper(LoanTypesDTO.class)
    List<LoanTypesDTO> getAllLoanTypes();

    @SqlQuery("""
            select ln.id as id, ln.loan_name_index as loan_name_index, ln.loan_name as loan_name from loan_name ln

            """)
    @RegisterBeanMapper(LoanNamesDTO.class)
    List<LoanNamesDTO> getAllLoanNames();

    @SqlQuery("""
            SELECT l.id as id, l.company_id as company_id, l.branch_id as branch_id, l.bank_id as bank_id, l.lender_id as lender_id, l.loan_type as loan_type, l.loan_number as loan_number, l.loan_name as loan_name,
            l.loan_amount as loan_amount, l.received_amount as received_amount, l.service_charge as service_charge, l.other_expenses as other_expenses
            	FROM loan l  where l.deleted=false <caseQuery> ;
            """)
    @RegisterBeanMapper(LoanDTO.class)
    List<LoanDTO> getLimitedLoanEntityByCompAndBranch(@Define String caseQuery);
}
