package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.PurchaseBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchaseBill")
@Slf4j
@RequiredArgsConstructor
public class PurchaseBillController {
    private final PurchaseBillService purchaseBillService;

    @GetMapping
    public ResponseDTO<List<PurchaseBillDTO>> getAllPurchaseBill(){
        try{
            return new ResponseDTO<List<PurchaseBillDTO>>(purchaseBillService.getAllPurchaseBills());
        } catch(Exception e){
            log.error("error occured while fetching purchase bills : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching purchase bills");
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO<PurchaseBillDTO> getSinglePurchasBillInfo(@PathVariable("id") Integer id){
        try{
            return new ResponseDTO<PurchaseBillDTO>(purchaseBillService.getSinglePurchasBillInfo(id));
        } catch(Exception e){
            log.error("error occured while fetching purchas bill with id " + id +" "+ e.getMessage());
            throw new CustomSqlException("error occured while fetching purchase bill with id " + id +" ");
        }
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseBill(@PathVariable int id){
        try{
            purchaseBillService.deletePurchaseBill(id);
        } catch(Exception e){
            log.error("error occured during purchaseBill deletion with id " + id + " "+ e.getMessage());
            throw new CustomSqlException("error occured while deletig the purchaseBill");
        }
    }

}
