package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PurchaseBillDAO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PurchaseBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseBillServiceImpl implements PurchaseBillService {
    private final DbService dbService;
    @Override
    public List<PurchaseBillDTO> getAllPurchaseBills() {
        return dbService.getDao(PurchaseBillDAO.class).getAllPurchaseBills();
    }

    @Override
    public PurchaseBillDTO getSinglePurchasBillInfo(Integer id) {
        return dbService.getDao(PurchaseBillDAO.class).getSinglePurchaseBill(id);
    }

    @Override
    public Integer addNewPurchaseBillInfo(PurchaseBillDTO purchaseBill) {
        return dbService.getDao(PurchaseBillDAO.class).addNewPurchaseBill(purchaseBill);
    }

    @Override
    public void updatePurchaseBill(PurchaseBillDTO productDTO, Integer id) {
        dbService.getDao(PurchaseBillDAO.class).updatePurchaseBill(productDTO, id);
    }

    @Override
    public void deletePurchaseBill(int id) {
        dbService.getDao(PurchaseBillDAO.class).deletePurchaseBill(id);
    }
}
