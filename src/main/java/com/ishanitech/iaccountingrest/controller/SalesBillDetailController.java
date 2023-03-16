package com.ishanitech.iaccountingrest.controller;
import com.ishanitech.iaccountingrest.service.SalesBillDetailService;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/salesBillDetail")
@RequiredArgsConstructor
public class SalesBillDetailController {

    private final SalesBillDetailService salesBillDetailService;


    @GetMapping("/{id}")
    public ResponseDTO<SalesBillDetailDTO> getSingleSalesInfo(@PathVariable int id){
        try{
            return new ResponseDTO<SalesBillDetailDTO>(salesBillDetailService.getSingleSalesInfo(id));
        } catch(Exception ex){
            log.error("error occured accesing sales info " + ex.getMessage());
            throw new CustomSqlException("error occured accesing sales info " + ex.getMessage());
        }
    }

}
