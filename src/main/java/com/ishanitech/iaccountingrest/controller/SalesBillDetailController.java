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
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SalesBillDetailController {

    private final SalesBillDetailService salesBillDetailService;
    @GetMapping
    public ResponseDTO<List<SalesBillDetailDTO>> getAllSalesInfo(){
        try{
            return new ResponseDTO<List<SalesBillDetailDTO>>(salesBillDetailService.getAllSalesInfo());
        } catch (Exception ex){
            log.error("error occured accessing sales infos " + ex.getMessage());

            throw new CustomSqlException("error occured accessing sales infos " + ex.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseDTO<SalesBillDetailDTO> getSingleSalesInfo(@PathVariable int id){
        try{
            return new ResponseDTO<SalesBillDetailDTO>(salesBillDetailService.getSingleSalesInfo(id));
        } catch(Exception ex){
            log.error("error occured accesing sales info " + ex.getMessage());
            throw new CustomSqlException("error occured accesing sales info " + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO<Integer> addSalesInfo(@RequestBody SalesBillDetailDTO salesBillDetailDTO){
        try{
            return new ResponseDTO<Integer>(salesBillDetailService.addNewSalesInfo(salesBillDetailDTO));
        } catch(Exception ex){
            log.error("error occured adding new sales info " + ex.getMessage());
            throw new CustomSqlException("error occured adding new sales info " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void editSalesInfo(@RequestBody SalesBillDetailDTO salesBillDetailDTO, @PathVariable int id){
        try{
            salesBillDetailService.editSalesInfo(salesBillDetailDTO, id);
        } catch(Exception ex){
            log.error("error occured editing sales info " + ex.getMessage());
            throw new CustomSqlException("error occured editing sales info " + ex.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public void deleteSaleInfo(@PathVariable int id){
        try{
            salesBillDetailService.deleteSalesById(id);
        }catch (Exception ex){
            log.error("error occured deleting sales info " + ex.getMessage());
            throw new CustomSqlException("error occured deleting sales info " + ex.getMessage());
        }
    }

}
