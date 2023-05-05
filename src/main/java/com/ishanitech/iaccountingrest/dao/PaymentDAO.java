package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PaymentDAO {

    @SqlQuery("SELECT p.sn as SN, p.company_id as companyId,p.party_id as partyId," +
            " p.amount as amount ,p.payment_mode_id as paymentModeId," +
            " p.tds_deducted as tdsDeducted, p.post_date_check as postDateCheck," +
            " p.branch_id as branchId, p.date as date ," +
            " pc.check_no as checkNo , pc.pay_date as postCheckDate" +
            " FROM payment p" +
            " LEFT JOIN post_date_check pc  " +
            " ON pc.payment_id = p.sn WHERE p.company_id = :companyId")
    @RegisterBeanMapper(PaymentDTO.class)
    List<PaymentDTO> getPaymentDetailsByCompanyId(@Bind int companyId);

    @SqlQuery("SELECT p.sn as SN, p.company_id as companyId,p.party_id as partyId," +
            " p.amount as amount ,p.payment_mode_id as paymentModeId," +
            " p.tds_deducted as tdsDeducted, p.post_date_check as postDateCheck," +
            " p.branch_id as branchId, p.date as date ," +
            " pc.check_no as checkNo , pc.pay_date as postCheckDate" +
            " FROM payment p" +
            " LEFT JOIN post_date_check pc  " +
            " ON pc.payment_id = p.sn WHERE p.sn = :SN")
    @RegisterBeanMapper(PaymentDTO.class)
    PaymentDTO getPaymentDetailsById(@Bind int SN);

    @SqlUpdate("INSERT INTO payment( " +
            " company_id, party_id, amount, payment_mode_id, tds_deducted, branch_id, date, post_date_check )" +
            " VALUES ( :companyId, :partyId, :amount, :paymentModeId, :tdsDeducted, :branchId, :date, :postDateCheck );")
    @RegisterBeanMapper(PaymentDTO.class)
    @GetGeneratedKeys
    Long addPaymentDetails(@BindBean PaymentDTO paymentDTO);


    @SqlUpdate("DELETE FROM payment WHERE sn= :SN")
    void deleteFromPayment(@Bind("SN") int SN);



}
