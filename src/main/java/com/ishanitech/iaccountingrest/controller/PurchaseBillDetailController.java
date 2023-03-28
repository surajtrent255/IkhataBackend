package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.PurchaseBillDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/purchaseBillDetail")
public class PurchaseBillDetailController {
    private final PurchaseBillDetailService purchaseBillDetailService;

    @GetMapping("/{id}")
    public ResponseDTO<PurchaseBillDetailDTO> getSinglePurchaseInfo(@PathVariable int id){
        try{
            return new ResponseDTO<PurchaseBillDetailDTO>(purchaseBillDetailService.getSinglePurchaseInfo(id));
        } catch(Exception ex){
            log.error("error occured accesing purchase info " + ex.getMessage());
            throw new CustomSqlException("error occured accesing purchase info " + ex.getMessage());
        }
    }
}
