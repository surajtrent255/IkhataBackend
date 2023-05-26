package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ReceiptDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.ReceiptService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/receipt")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;

    @GetMapping("/limited")
    public ResponseDTO<List<ReceiptDTO>> getLimitedReceiptsByCompId(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId){
        try{
            return new ResponseDTO<List<ReceiptDTO>>(receiptService.getLimitedReceiptsByCompIdAndBranchId(offset, pageTotalItems, compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the receipts infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the receipts infos : " );
        }
    }
    @GetMapping("/{companyId}")
    public ResponseDTO<?> getReceipts(@PathVariable("companyId") int companyId){
        try{
            return new ResponseDTO<>(receiptService.getReceipts(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }




    @PostMapping
    public ResponseDTO<?> addReceipts(@RequestBody ReceiptDTO receiptDTO){
        try{
          return new ResponseDTO<>("Successfully Added" + receiptService.addReceipts(receiptDTO)) ;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @DeleteMapping("/{SN}")
    public ResponseDTO<?> deleteReceipts(@PathVariable("SN") int SN){
        try {
            receiptService.deleteReceipts(SN);
            return new ResponseDTO<>("Successfully Deleted");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
