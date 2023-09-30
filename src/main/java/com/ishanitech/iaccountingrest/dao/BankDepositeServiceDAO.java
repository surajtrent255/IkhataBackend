package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.BankDepositDTO;
import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BankDepositeServiceDAO {

    @SqlQuery("SELECT * FROM bank_deposit where company_id=:companyId AND branch_id=:branchId")
    @RegisterBeanMapper(BankDepositDTO.class)
    List<BankDepositDTO> getALLBankDeposite(int companyId, int branchId);





    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO bank_deposit( bank_id, company_id, branch_id, deposit_amount, deposit_type , submit_date ,cheque_number, deposited_by, nepali_date, english_date) " +
            "VALUES (:bankId, :companyId,  :branchId, :depositAmount,:depositType, :submitDate, :chequeNumber, :depositedBy,  :nepaliDate, :englishDate)")
    int addBankDeposit(@BindBean BankDepositDTO BankDepositeDTO);



@SqlUpdate("UPDATE bank_deposit SET " +
"bank_id= :bankId, " +
"company_id = :companyId, " +
"branch_id = :branchId,"+
"deposit_amount = :depositAmount,"+
"deposit_type = :depositType,"+
        "submit_date = :submitDate,"+
        "nepali_date = :nepaliDate,"+
        "english_date = :englishDate,"+
"cheque_number = :chequeNumber"+" WHERE deposit_id  = :depositId ")
    int updateDeposite(@BindBean BankDepositDTO bankDepositeDTO, @Bind int depositId);
    @SqlQuery("DELETE FROM bank_deposit WHERE  branch_id= :branchId AND deposit_id= :depositId")
    int deleteDeposite(@Bind int branchId ,@Bind int depositId);

    @SqlQuery("""
            select * from bank_deposit bd where bd.company_id = :compId and bd.branch_id = :branchId order by deposit_id desc limit :pageTotalItems offset (:offset -1) ;
            """)
    @RegisterBeanMapper(BankDepositDTO.class)
    List<BankDepositDTO> getLimitedBankDepositByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);

    @SqlQuery("""
        select * from bank_deposit bd where bd.company_id = :companyId and bd.branch_id = :branchId and bd.deposit_id = :id;
            """)
    @RegisterBeanMapper(BankDepositDTO.class)
    BankDepositDTO getSingleBankDeposit(Integer id, Integer companyId, Integer branchId);
}
