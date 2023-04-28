package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.AccountTypeDTO;
import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.BankListDTO;
import com.ishanitech.iaccountingrest.dto.TypePaymentDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BankDAO {
    @SqlQuery("SELECT * FROM bank where bank_id=:id")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO> getAllByBankId(int id);

    @SqlQuery("SELECT * FROM bank where bank_name=:bankName")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO> getAllByBankName(String bankName);

    @SqlQuery("SELECT * FROM bank ")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO>  getAllByBank();

    @SqlQuery("SELECT * FROM bank where company_id=:companyId")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO> getAllByBankCompany(int companyId);

    @SqlQuery("SELECT * FROM bank where company_id=:companyId AND branch_id=:branchId")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO> getALLBYBranch(int companyId, int branchId);
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO bank( "
            + " company_id,"
            + " branch_id,"
            + " bank_name,"
            + " account_number,"
            +"account_type,"
            +"initial_amount"
            + " ) "
            + " VALUES ("
            + " :companyId, "
            + " :branchId, "
            + " :bankName,"
            + " :accountNumber,"
            + " :accountType,"
            + " :initialAmount "
            + "  )")
    int addBank(@BindBean BankDTO bankDTO);
    @SqlUpdate("UPDATE bank SET company_id = :companyId, "
            + "branch_id = :branchId, bank_name = :bankName,  account_number = :accountNumber, "
            + "account_type = :accountType, initial_amount = :initialAmount WHERE bank_id  = :Id")
    int editbank(@BindBean BankDTO bankDTO, @Bind int Id);

    @SqlQuery("DELETE FROM bank WHERE  company_id=:companyId AND branch_id=branchId")
    int deleteBank(@Bind int companyId, @Bind int branchId);

    @SqlQuery("DELETE FROM bank WHERE account_number=:accountNo")
    int deleteFromBankByAccountNo(@Bind Long accountNo );




    @SqlQuery("SELECT * FROM bank_list ")
    @RegisterBeanMapper(BankListDTO.class)
    List<BankListDTO> getAllBankList();
    @SqlQuery("SELECT * FROM type_of_payment ")
    @RegisterBeanMapper(TypePaymentDTO.class)
    List<TypePaymentDTO> getAllPayment();
    @SqlQuery("SELECT * FROM account_type ")
    @RegisterBeanMapper(AccountTypeDTO.class)
    List<AccountTypeDTO> getAccountType();
}
