package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PaymentDAO;
import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PaymentService;
import org.jdbi.v3.core.JdbiException;
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
    public Integer addPaymentDetails(PaymentDTO paymentDTO) {
        PaymentDAO paymentDAO = dbService.getDao(PaymentDAO.class);
        int success = 0;
        try{
            success = paymentDAO.addPayMentDetails(paymentDTO);
            return success;
        }catch (JdbiException e){
            System.out.println(e);
        }
        return success;
    }


}
