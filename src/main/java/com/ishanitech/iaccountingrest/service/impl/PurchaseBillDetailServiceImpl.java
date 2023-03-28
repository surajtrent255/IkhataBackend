package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PurchaseBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PurchaseBillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseBillDetailServiceImpl implements PurchaseBillDetailService {
    private final DbService dbService;

    @Override
    public PurchaseBillDetailDTO getSinglePurchaseInfo(int id) {
        return dbService.getDao(PurchaseBillDetailDAO.class).getSinglePurchaseInfoById(id);
    }
}
