package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillMasterDTO;
import com.ishanitech.iaccountingrest.service.PurchaseBillMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/createPurchaseBill")
@RestController
@RequiredArgsConstructor
public class PurchaseBillMasterController {

    private final PurchaseBillMasterService purchaseBillMasterService;
    @GetMapping
    public ResponseDTO<List<PurchaseBillMasterDTO>> getAllPurchaseBills(){
        return new ResponseDTO<List<PurchaseBillMasterDTO>>(purchaseBillMasterService.getAllPurchaseBills());
    }

    @GetMapping("/{id}")
    public ResponseDTO<PurchaseBillMasterDTO> getSinglePurchaseBill(@PathVariable("id") int id){
        return new ResponseDTO<PurchaseBillMasterDTO>(purchaseBillMasterService.getSinglePurchaseBill());
    }

    @PostMapping
    public ResponseDTO<Integer> addSaleBils(@RequestBody PurchaseBillMasterDTO purchaseBillMasterDTO){
        return purchaseBillMasterService.addNewPurchaseBill(purchaseBillMasterDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable("id") int id){
        purchaseBillMasterService.deleteBill(id);
    }

}
