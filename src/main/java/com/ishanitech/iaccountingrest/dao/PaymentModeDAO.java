package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PaymentModeDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface PaymentModeDAO {
    @SqlQuery("SELECT * FROM payment_mode")
    @RegisterBeanMapper(PaymentModeDTO.class)
    List<PaymentModeDTO> getPaymentModeDetails();
}
