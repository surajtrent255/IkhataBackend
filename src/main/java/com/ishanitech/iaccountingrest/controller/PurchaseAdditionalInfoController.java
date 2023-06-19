package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PurchaseAdditionalInfoDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.exception.FileStorageException;
import com.ishanitech.iaccountingrest.service.PurchaseAdditionalInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/purchase/additionalInfo")
public class PurchaseAdditionalInfoController {

    private final PurchaseAdditionalInfoService purchaseAdditionalInfoService;

    @PostMapping
    public ResponseDTO<?> addPurchaseAdditionalInfo(@RequestBody PurchaseAdditionalInfoDTO purchaseAdditionalInfoDTO){
        try{
            purchaseAdditionalInfoService.addPurchaseAdditionalInfo(purchaseAdditionalInfoDTO);
            return new ResponseDTO<>("Successfully Added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new FileStorageException(e.getMessage());
        }
    }

    @GetMapping("/attribute")
    public ResponseDTO<?> getPurchaseAdditionalAttribute(@RequestParam("companyId") int companyId){
        try{
            return new ResponseDTO<>(purchaseAdditionalInfoService.getPurchaseAdditionalAttributes(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PostMapping("/add/attribute")
    public ResponseDTO<?> addNewAttribute(@RequestParam("attributeName") String attributeName,@RequestParam("companyId") int companyId){
        try{
            purchaseAdditionalInfoService.addNewAttributes(attributeName,companyId);
            return new ResponseDTO<>("successfully added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
