package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BankWithdrawDAO {
    @SqlQuery("SELECT * FROM  bank_withdraw where company_id=:companyId AND branch_id=:branchId")
    @RegisterBeanMapper(BankWithdrawDTO.class)
    List<BankWithdrawDTO> getAllwithdraw(int companyId, int branchId);




    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO bank_withdraw( "
            + " company_id, "
            + " branch_id, "
            + " withdraw_amount, "
            + " withdraw_type, "
            +" withdraw_date, "
            +" cheque_number "
            + " ) "
            + " VALUES ("
            + " :companyId, "
            + " :branchId, "
            + " :withdrawAmount,"
            + " :withdrawType,"
            + " :withdrawDate,"
            + " :chequeNumber "
            + "  )")
    @RegisterBeanMapper(BankWithdrawDTO.class)
    int addwithdraw(BankWithdrawDTO bankWithdrawDTO);



}
