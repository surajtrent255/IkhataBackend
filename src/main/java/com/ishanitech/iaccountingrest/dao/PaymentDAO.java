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

    @SqlQuery("SELECT * from payment WHERE company_id = :companyId")
    @RegisterBeanMapper(PaymentDTO.class)
    List<PaymentDTO> getPaymentDetailsByCompanyId(@Bind int companyId);

    @SqlQuery("SELECT * from payment WHERE sn = :SN")
    @RegisterBeanMapper(PaymentDTO.class)
    PaymentDTO getPaymentDetailsById(@Bind int SN);

    @SqlUpdate("INSERT INTO payment( " +
            " company_id, party_id, amount, payment_mode_id, tds_deducted, branch_id, date, post_datecheck )" +
            " VALUES ( :companyId, :partyId, :amount, :paymentModeId, :tdsDeducted, :branchId, :date, :postDateCheck );")
    @RegisterBeanMapper(PaymentDTO.class)
    @GetGeneratedKeys
    Long addPaymentDetails(@BindBean PaymentDTO paymentDTO);


    @SqlUpdate("DELETE FROM payment WHERE sn= :SN")
    @Nullable
    void deleteFromPayment(@Bind("SN") int SN);



}
