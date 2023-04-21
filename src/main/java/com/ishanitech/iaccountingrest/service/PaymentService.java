package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> getPaymentDetailsByCompanyId(int companyId);

    Integer addPaymentDetails(PaymentDTO paymentDTO);

}
