package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PaymentModeDAO;
import com.ishanitech.iaccountingrest.dto.PaymentModeDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PaymentModeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentModeServiceImpl implements PaymentModeService {
    private final DbService dbService;

    public PaymentModeServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<PaymentModeDTO> getAllPaymentModeDetails() {
        PaymentModeDAO paymentModeDAO = dbService.getDao(PaymentModeDAO.class);
        return paymentModeDAO.getPaymentModeDetails();
    }
}
