package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.VatRateTypesDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.VatRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vatRateType")
@Slf4j
@RequiredArgsConstructor
public class VatRateController {

    private final VatRateService vatRateService;
    @GetMapping
    public ResponseDTO<List<VatRateTypesDTO>> getAllVatRateTypes (){
        try{
            return new ResponseDTO<List<VatRateTypesDTO>>(vatRateService.getAllVatRateTypes());
        } catch(Exception ex){
            log.error("error occured while fetching vat rates types " + ex.getMessage());
            throw new CustomSqlException(" Something went wrong while fetching vat rates types " + ex.getMessage());
        }
    }
}
