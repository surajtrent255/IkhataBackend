package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PaymentDAO;
import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final DbService dbService;

    public PaymentServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<PaymentDTO> getPaymentDetailsByCompanyId(int companyId) {
        PaymentDAO paymentDAO = dbService.getDao(PaymentDAO.class);
        return paymentDAO.getPaymentDetailsByCompanyId(companyId);
    }

    @Override
    public PaymentDTO getPaymentDetailsById(int SN) {
        PaymentDAO paymentDAO = dbService.getDao(PaymentDAO.class);
        return paymentDAO.getPaymentDetailsById(SN);
    }

    @Override
    public Long addPaymentDetails(PaymentDTO paymentDTO) {
        PaymentDAO paymentDAO = dbService.getDao(PaymentDAO.class);
        long success;
            success = paymentDAO.addPaymentDetails(paymentDTO);
            return success;
    }

    @Override
    public void DeletePaymentDetails(int SN) {
        PaymentDAO paymentDAO = dbService.getDao(PaymentDAO.class);
           paymentDAO.deleteFromPayment(SN);

    }

    @Override
    public void updatePaymentDetails(PaymentDTO paymentDTO) {
        PaymentDAO paymentDAO = dbService.getDao(PaymentDAO.class);
        paymentDAO.updatePaymentDetails(paymentDTO);
    }


}
