package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.service.SaleBillMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/createSalesBill")
@RestController
@RequiredArgsConstructor
public class SaleBillMasterController {

    private final SaleBillMasterService saleBillMasterService;

    @PostMapping
    public ResponseDTO<Integer> addSaleBills(@RequestBody SaleBillMasterDTO saleBillMasterDTO){
        return saleBillMasterService.addNewSaleBill(saleBillMasterDTO);
    }
}
