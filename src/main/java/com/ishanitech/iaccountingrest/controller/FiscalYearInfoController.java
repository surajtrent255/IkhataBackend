package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.FiscalYearDTO;
import com.ishanitech.iaccountingrest.dto.FiscalYearInfo;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.FiscalYearInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/fiscalYearInfo")
public class FiscalYearInfoController {

    private final FiscalYearInfoService fiscalYearInfoService;

    @PostMapping("/createNewFiscalYear")
    private ResponseDTO<Integer> createNewFiscalYearInfo(@RequestBody FiscalYearInfo fiscalYInfo){
        try{
            return new ResponseDTO<>(fiscalYearInfoService.createNewFiscalYearInfo(fiscalYInfo));
        } catch (Exception ex){
            log.error("error creating new  fiscalYear info >>>>>>>>>>>>> " + ex.getMessage());
            throw new CustomSqlException("Something went wrong while creating new Fiscal Year");
        }
    }

    @GetMapping("/active")
    private ResponseDTO<FiscalYearInfo> getActiveFiscalYearInfo(){
        try{
            return new ResponseDTO<>(fiscalYearInfoService.getActiveFiscalYearInfo());
        } catch (Exception ex){
            throw new CustomSqlException(" Something went wrong while fetching active fiscal year info ");
        }
    }



}
