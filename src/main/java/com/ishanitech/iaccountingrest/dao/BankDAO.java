package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.*;
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

    @SqlQuery("SELECT * FROM bank order by bank_id desc")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO>  getAllByBank();

    @SqlQuery("SELECT * FROM bank where company_id=:companyId")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO> getAllByBankCompany(int companyId);

    @SqlQuery("SELECT * FROM bank where company_id=:companyId AND branch_id=:branchId order by bank_id desc")
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO> getALLBYBranch(int companyId, int branchId);
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO bank( "
            + " company_id,"
            + " branch_id,"
            + " bank_type_id,"
            + " account_number,"
            +" account_type, "
            +" initial_amount "
            + " ) "
            + " VALUES ("
            + " :companyId, "
            + " :branchId, "
            + " :bankTypeId,"
            + " :accountNumber,"
            + " :accountType,"
            + " :initialAmount "
            + "  )")
    int addBank(@BindBean BankDTO bankDTO);
    @SqlUpdate("UPDATE bank SET company_id = :companyId, "
            + "branch_id = :branchId, bank_type_id = :bankTypeId,  account_number = :accountNumber, "
            + "account_type = :accountType, initial_amount = :initialAmount WHERE bank_id  = :Id")
    int editbank(@BindBean BankDTO bankDTO, @Bind int Id);

    @SqlQuery("DELETE FROM bank WHERE  company_id=:companyId AND branch_id=:branchId")
    int deleteBank(@Bind int companyId, @Bind int branchId);

    @SqlQuery("DELETE FROM bank WHERE bank_id=:bankId")
    int deleteFromBankByAccountNo(@Bind int bankId );




    @SqlQuery("SELECT * FROM bank_list ")
    @RegisterBeanMapper(BankListDTO.class)
    List<BankListDTO> getAllBankList();
    @SqlQuery("SELECT * FROM type_of_payment ")
    @RegisterBeanMapper(TypePaymentDTO.class)
    List<TypePaymentDTO> getAllPayment();
    @SqlQuery("SELECT * FROM account_type ")
    @RegisterBeanMapper(AccountTypeDTO.class)
    List<AccountTypeDTO> getAccountType();

    @SqlQuery("""
            select * from bank b where b.company_id = :compId and b.branch_id = :branchId order by bank_id desc limit :pageTotalItems offset (:offset -1) ;
            """)
    @RegisterBeanMapper(BankDTO.class)
    List<BankDTO> getLimitedBanksByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
