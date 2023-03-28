package com.ishanitech.iaccountingrest.service.impl;
import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.service.BillService;

import com.ishanitech.iaccountingrest.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final DbService dbService;
    @Override
    public List<SalesBillDTO> getAllBills() {
        return dbService.getDao(SalesBillDAO.class).getAllBills();
    }

    @Override
    public SalesBillDTO getBillById(int id) {
        return dbService.getDao(SalesBillDAO.class).getBillById(id);
    }

    @Transactional
    @Override
    public void deleteBillById(int id) {
         dbService.getDao(SalesBillDAO.class).deleteBillById(id);
    }

    @Override
    public Integer printTheBill(int billId, int userId) {
       return dbService.getDao(SalesBillDAO.class).printTheBillWithBillId(billId, new Date(), userId);
    }
}