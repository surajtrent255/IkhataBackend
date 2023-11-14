package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillInvoice;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.PurchaseBillDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/purchaseBillDetail")
public class PurchaseBillDetailController {
    private final PurchaseBillDetailService purchaseBillDetailService;

    @GetMapping("/{id}")
    public ResponseDTO<List<PurchaseBillDetailDTO>> getSinglePurchaseInfo(@PathVariable int id){
        try{
            return new ResponseDTO<List<PurchaseBillDetailDTO>>(purchaseBillDetailService.getSinglePurchaseInfo(id));
        } catch(Exception ex){
            log.error("error occured accesing purchase info " + ex.getMessage());
            throw new CustomSqlException("error occured accesing purchase info " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseDTO<PurchaseBillInvoice> getPurchaseInfoByBillId(@RequestParam("billId") int billId,
                                                                    @RequestParam("companyId") int companyId, @RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<PurchaseBillInvoice>(purchaseBillDetailService.getPurchaseInfoByBillId(billId, companyId, branchId));
        } catch(Exception ex){
            log.error("error occured accesing purchase info by BillId" + ex.getMessage());
            throw new CustomSqlException("error occured accesing purchase info ByBillId" );
        }
    }
}
