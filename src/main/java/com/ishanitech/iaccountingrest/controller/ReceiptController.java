package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ReceiptDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.ReceiptService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/receipt")
@RequiredArgsConstructor
public class ReceiptController {
    private final ReceiptService receiptService;
    @GetMapping("/{companyId}")
    public ResponseDTO<?> getReceipts(@PathVariable("companyId") int companyId){
        try{
            return new ResponseDTO<>(receiptService.getReceipts(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Done");
    }

    @PostMapping
    public ResponseDTO<?> addReceipts(@RequestBody ReceiptDTO receiptDTO){
        try{
          return new ResponseDTO<>(receiptService.addReceipts(receiptDTO)) ;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Successfully Added");
    }

    @DeleteMapping("/{SN}")
    public ResponseDTO<?> deleteReceipts(@PathVariable("SN") int SN){
        try {
            receiptService.deleteReceipts(SN);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Successfully Deleted");
    }
}
