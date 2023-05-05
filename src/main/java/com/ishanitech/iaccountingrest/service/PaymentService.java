package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> getPaymentDetailsByCompanyId(int companyId);

    PaymentDTO getPaymentDetailsById(int SN);

    Long addPaymentDetails(PaymentDTO paymentDTO);

    void DeletePaymentDetails(int SN);



}
