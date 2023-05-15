package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import org.jdbi.v3.sqlobject.customizer.BindBean;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> getPaymentDetailsByCompanyId(int companyId);

    PaymentDTO getPaymentDetailsById(int SN);

    Long addPaymentDetails(PaymentDTO paymentDTO);

    void DeletePaymentDetails(int SN);

    void updatePaymentDetails( PaymentDTO paymentDTO);



}
