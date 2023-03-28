package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillMasterDTO;
import com.ishanitech.iaccountingrest.model.User;
import com.ishanitech.iaccountingrest.service.PurchaseBillMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/createPurchaseBill")
@RestController
@RequiredArgsConstructor
public class PurchaseBillMasterController {

    private final PurchaseBillMasterService purchaseBillMasterService;

    @PostMapping
    public ResponseDTO<Integer> addSaleBils(@RequestBody PurchaseBillMasterDTO purchaseBillMasterDTO){
        return purchaseBillMasterService.addNewPurchaseBill(purchaseBillMasterDTO);
    }



}

