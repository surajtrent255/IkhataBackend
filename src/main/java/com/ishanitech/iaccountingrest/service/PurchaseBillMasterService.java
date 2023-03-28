package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PurchaseBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;

import java.util.List;

public interface PurchaseBillMasterService {
    List<PurchaseBillMasterDTO> getAllPurchaseBills();

    PurchaseBillMasterDTO getSinglePurchaseBill();

    ResponseDTO<Integer> addNewPurchaseBill(PurchaseBillMasterDTO purchaseBillMasterDTO);

    void deleteBill(int id);
}
