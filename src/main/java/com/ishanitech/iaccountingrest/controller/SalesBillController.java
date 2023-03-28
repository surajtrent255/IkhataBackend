package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salesBill")
@Slf4j
@RequiredArgsConstructor
public class SalesBillController {

    private final BillService billService;

    @GetMapping
    public ResponseDTO<List<SalesBillDTO>> getAllBills(){
        try{
            return new ResponseDTO<List<SalesBillDTO>>(billService.getAllBills());
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO<SalesBillDTO> getBillById(@PathVariable int id){
        try{
            return new ResponseDTO<SalesBillDTO>(billService.getBillById(id));
        } catch(Exception e) {
            log.error("Error occured accessing the bill info : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill info : " );
        }
    }


    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable("id") int id){
        try{
            billService.deleteBillById(id);
        } catch(Exception ex){
            log.error("Error occured deleting the bill info " + ex.getMessage());
            throw new CustomSqlException("Error occured deleting the bill info : " + ex.getMessage());
        }
    }

    @PostMapping("/print/{billNo}")
    public ResponseDTO<Integer> printTheBill(@PathVariable int billNo, @RequestBody Map<String, Integer> printMap){
        try{
            return new ResponseDTO<Integer>(billService.printTheBill(billNo, printMap.get("printerId")));
        } catch(Exception ex){
            log.error("error occured while updating printing info");
            throw new CustomSqlException("Something went wrong while updating printing info");
        }
    }

}
