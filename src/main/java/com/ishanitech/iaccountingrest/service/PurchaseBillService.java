package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;

import java.util.List;

public interface PurchaseBillService {
    List<PurchaseBillDTO> getAllPurchaseBills();

    PurchaseBillDTO getSinglePurchasBillInfo(Integer id);

    void deletePurchaseBill(int id);
}
