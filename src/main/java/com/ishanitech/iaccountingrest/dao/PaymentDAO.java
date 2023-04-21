package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import com.ishanitech.iaccountingrest.dto.PaymentModeDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PaymentDAO {

    @SqlQuery("SELECT * from payment WHERE company_id = :companyId")
    @RegisterBeanMapper(PaymentDTO.class)
    List<PaymentDTO> getPaymentDetailsByCompanyId(@Bind int companyId);

    @SqlUpdate("INSERT INTO payment( " +
            " company_id, party_id, amount, payment_mode_id, tds_deducted, branch_id, date )" +
            " VALUES ( :companyId, :partyId, :amount, :paymentModeId, :tdsDeductedAmount, :branchId, :date);")
    @RegisterBeanMapper(PaymentDTO.class)
    Integer addPayMentDetails(@BindBean PaymentDTO paymentDTO);

//    Payment Mode Query



}
