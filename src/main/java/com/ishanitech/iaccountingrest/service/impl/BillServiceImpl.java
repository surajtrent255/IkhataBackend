package com.ishanitech.iaccountingrest.service.impl;
import com.ishanitech.iaccountingrest.dao.BillDAO;
import com.ishanitech.iaccountingrest.dto.BillDTO;
import com.ishanitech.iaccountingrest.service.BillService;

import com.ishanitech.iaccountingrest.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final DbService dbService;
    @Override
    public List<BillDTO> getAllBills() {
        return dbService.getDao(BillDAO.class).getAllBills();
    }

    @Override
    public BillDTO getBillById(int id) {
        return dbService.getDao(BillDAO.class).getBillById(id);
    }

    @Override
    public Integer addNewBill(BillDTO billDTO) {
        billDTO.setDate(new Date());
        return dbService.getDao(BillDAO.class).addNewBill(billDTO);
    }

    @Override
    public void updateBill(BillDTO billDTO, int id) {
         dbService.getDao(BillDAO.class).updateBill(billDTO, id);
    }

    @Override
    public void deleteBillById(int id) {
         dbService.getDao(BillDAO.class).deleteBillById(id);
    }
}