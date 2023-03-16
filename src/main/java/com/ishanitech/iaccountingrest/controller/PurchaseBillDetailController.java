package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/purchaseBillDetail")
public class PurchaseBillDetailController {
//    private final PurchaseBillDetailService purchaseBillDetailService;
//
//    @GetMapping
//    public ResponseDTO<List<PurchaseBillDetailDTO> getAllPurchaseBillDetails(){
//        try{
//
//        } catch(Exception e){
//            log.error("error occured while ");
//
//            throw new CustomSqlException("error occured whle fetching products details");
//        }
//    }
}
