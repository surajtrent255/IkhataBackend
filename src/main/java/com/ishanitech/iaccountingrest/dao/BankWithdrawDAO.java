package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

public interface BankWithdrawDAO {
    @SqlQuery("SELECT * FROM  bank_withdraw where company_id=:companyId AND branch_id=:branchId")
    @RegisterBeanMapper(BankWithdrawDTO.class)
    List<BankWithdrawDTO> getAllwithdraw(int companyId, int branchId);




    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO bank_withdraw ( bank_id, company_id , branch_id , withdraw_amount , withdraw_type , withdraw_date , cheque_number) VALUES ( :bankId, :companyId ,  :branchId ,  :withdrawAmount ,:withdrawType , :withdrawDate, :chequeNumber )")
    int addwithdraw(@BindBean BankWithdrawDTO bankWithdrawDTO);

    @SqlUpdate("UPDATE bank_withdraw SET " +
            "bank_id = : bankId"+
            "company_id = :companyId, " +
            "branch_id = :branchId, " +
            "withdraw_amount = :withdrawAmount,"+
            "withdraw_type = :withdrawType,"+
            "withdraw_date = :withdrawDate,"+
            "cheque_number = :chequeNumber"+" WHERE withdraw_id  = :withdrawId ")
//    @RegisterBeanMapper(BankWithdrawDTO.class)
    void updatewithdraw(@BindBean BankWithdrawDTO bankWithdrawDTO, @Bind int withdrawId);
    @SqlQuery("DELETE FROM bank_withdraw WHERE branch_id=:branchId AND withdraw_id  = :withdrawId ")
    int deletewithdraw(int branchId, int withdrawId);



    @SqlQuery("""
            select * from bank_withdraw bw where bw.company_id = :compId and bw.branch_id = :branchId order by withdraw_id desc limit :pageTotalItems offset (:offset -1) ;
            """)
    @RegisterBeanMapper(BankWithdrawDTO.class)
    List<BankWithdrawDTO> getLimitedWithDrawsByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
