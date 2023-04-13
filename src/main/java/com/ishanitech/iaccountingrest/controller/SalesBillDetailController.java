package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillInvoiceDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.SalesBillDetailService;
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

    @GetMapping("/getByBillId")
    public ResponseDTO<List<SalesBillDetailDTO>> getSalesDetailByBillId(@RequestParam("billId") int billId){
        try{
            return new ResponseDTO<List<SalesBillDetailDTO>>(salesBillDetailService.getSaleBillDetailByBillId(billId));
        } catch(Exception ex){
            log.error("error occured accesing sales info by BillId" + ex.getMessage());
            throw new CustomSqlException("error occured accesing sales info ByBillId" + ex.getMessage());
        }
    }
    @GetMapping
    public ResponseDTO<SalesBillInvoiceDTO> getSalesInfoByBillId(@RequestParam("billId") int billId){
        try{
            return new ResponseDTO<SalesBillInvoiceDTO>(salesBillDetailService.getSalesInfoByBillId(billId));
        } catch(Exception ex){
            log.error("error occured accesing sales info by BillId" + ex.getMessage());
            throw new CustomSqlException("error occured accesing sales info ByBillId" + ex.getMessage());
        }
    }


}
