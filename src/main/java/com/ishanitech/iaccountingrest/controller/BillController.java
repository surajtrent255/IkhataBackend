package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.BillDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
@Slf4j
@RequiredArgsConstructor
public class BillController {

    private BillService billService;

    @GetMapping
    public ResponseDTO<List<BillDTO>> getAllBills(){
        try{
            return new ResponseDTO<List<BillDTO>>(billService.getAllBills());
        } catch(Exception e) {
            log.info("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public BillDTO getBillById(@PathVariable int id){
        try{
            return new ResponseDTO<BillDTO>(billService.getBillById());
        } catch(Exception e) {
            log.info("Error occured accessing the bill info : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill info : " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO<Integer> addNewBill(){
        try{
            return new ResponseDTO<BillDTO>(billService.addNewBill());
        } catch(Exception e) {
            log.info("Error occured creating the bill info : " + e.getMessage());
            throw new CustomSqlException("Error occured creating the bill info : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public BillDTO updateBill(@RequestBody BillDTO billDTO, @PathVariable int id){
        try{
            return new ResponseDTO<BillDTO>(billService.updateBill(billDTO, id));
        } catch(Exception e) {
            log.info("Error occured updating the bill info : " + e.getMessage());
            throw new CustomSqlException("Error occured updating the bill info :" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable("id") int id){
        try{
            billService.deleteBillById(id);
        } catch(Exception ex){
            log.info("Error occured deleting the bill info " + ex.getMessage());
            throw new CustomSqlException("Error occured deleting the bill info : " + ex.getMessage());
        }
    }


}
