package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.FiscalYearDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.SaleBillMasterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/createSalesBill")
@RestController
@RequiredArgsConstructor
@Slf4j
public class SaleBillMasterController {

    private final SaleBillMasterService saleBillMasterService;

    @PostMapping
    public ResponseDTO<Integer> addSaleBills(@RequestBody SaleBillMasterDTO saleBillMasterDTO){
        return saleBillMasterService.addNewSaleBill(saleBillMasterDTO);
    }

    @PostMapping("/changeFiscalYear")
    public  void generateBillsForNewFiscalYearForEachCompanyAndBranch(@RequestBody FiscalYearDTO fiscalYear){
        try{
             saleBillMasterService.changeFiscalYear(fiscalYear.getFiscalYear());
        } catch (Exception jex){
            log.error("error generating new FiscalYearForEach Compnay ANd Branch" + jex.getMessage()) ;
            throw new CustomSqlException("something went wrong");
        }
    }
}
