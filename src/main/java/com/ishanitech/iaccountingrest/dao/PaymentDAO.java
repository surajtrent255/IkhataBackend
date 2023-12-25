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
                        " p.branch_id as branchId, p.date as date , p.status as paymentStatus ," +
                        " p.check_no as checkNo , p.bank_name as bankName , pc.pay_date as postCheckDate," +
                        " p.nepali_date as nepaliDate" +
                        " FROM payment p" +
                        " LEFT JOIN post_date_check pc  " +
                        " ON pc.payment_id = p.sn WHERE p.company_id = :companyId")
        @RegisterBeanMapper(PaymentDTO.class)
        List<PaymentDTO> getPaymentDetailsByCompanyId(@Bind int companyId);

        @SqlQuery("SELECT p.sn as SN, p.company_id as companyId,p.party_id as partyId," +
                        " p.amount as amount ,p.payment_mode_id as paymentModeId," +
                        " p.tds_deducted as tdsDeducted, p.post_date_check as postDateCheck," +
                        " p.branch_id as branchId, p.date as date ,p.status as paymentStatus ," +
                        " p.check_no as checkNo, p.bank_name as bankName , pc.pay_date as postCheckDate," +
                        " p.nepali_date as nepaliDate" +
                        " FROM payment p" +
                        " LEFT JOIN post_date_check pc  " +
                        " ON pc.payment_id = p.sn WHERE p.sn = :SN")
        @RegisterBeanMapper(PaymentDTO.class)
        PaymentDTO getPaymentDetailsById(@Bind int SN);

        @SqlUpdate("INSERT INTO payment( " +
                        " company_id, party_id, amount, payment_mode_id, check_no , bank_name , tds_deducted, branch_id, date,nepali_date, post_date_check )"
                        +
                        " VALUES ( :companyId, :partyId, :amount, :paymentModeId, :checkNo , :bankName , :tdsDeducted, :branchId, :date,:nepaliDate, :postDateCheck );")
        @RegisterBeanMapper(PaymentDTO.class)
        @GetGeneratedKeys
        Long addPaymentDetails(@BindBean PaymentDTO paymentDTO);

        @SqlUpdate("DELETE FROM payment WHERE sn= :SN")
        void deleteFromPayment(@Bind("SN") int SN);

        @SqlUpdate("""
                        UPDATE payment
                        	SET  party_id=:partyId, amount=:amount, payment_mode_id=:paymentModeId,check_no = :checkNo, tds_deducted=:tdsDeducted, post_date_check=:postDateCheck
                        	WHERE sn=:SN;
                        """)
        @RegisterBeanMapper(PaymentDTO.class)
        void updatePaymentDetails(@BindBean PaymentDTO paymentDTO);

        @SqlQuery("""
                        select Sum(amount) from payment where party_id = :partyId and company_id = :companyId and branch_id = :branchId;
                        """)
        Double getTotalPaymentByPartyIdForCreditors(String partyId, int companyId, int branchId);

        @SqlQuery("""
                        SELECT party_id, amount from payment where company_id = :companyId and branch_id = :branchId
                        """)
        @RegisterBeanMapper(PaymentDTO.class)
        List<PaymentDTO> getAllPaymentByCompanyIdAndBranchId(int companyId, int branchId);

        @SqlQuery("SELECT p.sn as SN, p.company_id as companyId,p.party_id as partyId," +
                        " p.amount as amount ,p.payment_mode_id as paymentModeId," +
                        " p.tds_deducted as tdsDeducted, p.post_date_check as postDateCheck," +
                        " p.branch_id as branchId, p.date as date , p.status as paymentStatus ," +
                        " p.check_no as checkNo, p.bank_name as bankName , pc.pay_date as postCheckDate," +
                        " p.nepali_date as nepaliDate" +
                        " FROM payment p" +
                        " LEFT JOIN post_date_check pc  " +
                        " ON pc.payment_id = p.sn WHERE p.company_id = :compId  and p.branch_id = :branchId order by p.sn desc limit :pageTotalItems offset (:offset -1) ;")
        @RegisterBeanMapper(PaymentDTO.class)
        List<PaymentDTO> getLimitedSalesBillByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId,
                        Integer branchId);
}
