package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PurchaseAdditionalInfoDAO;
import com.ishanitech.iaccountingrest.dto.PurchaseAdditionalInfoDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PurchaseAdditionalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseAdditionalInfoServiceImpl implements PurchaseAdditionalInfoService {

    @Autowired
    private final DbService dbService;

    @Override
    public void addPurchaseAdditionalInfo(PurchaseAdditionalInfoDTO purchaseAdditionalInfoDTO) {
        PurchaseAdditionalInfoDAO purchaseAdditionalInfoDAO = dbService.getDao(PurchaseAdditionalInfoDAO.class);
        purchaseAdditionalInfoDAO.addPurchaseAdditionalInfo(purchaseAdditionalInfoDTO);
    }
}
