package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.BankWithdrawService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/bank/withdraw")
@RequiredArgsConstructor
public class BankWithdrawController {
    private final BankWithdrawService bankWithdrawService;

    @GetMapping("/{companyId}/{branchId}")
    public ResponseDTO<?> getAllWithdraw(@PathVariable("companyId") int companyId ,@PathVariable("branchId") int branchId){
        try{
            return new ResponseDTO<>(bankWithdrawService.getAllWithdraw(companyId,branchId));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(bankWithdrawService.getAllWithdraw(companyId, branchId));
    }

    @PostMapping()
    public ResponseDTO<?> addwithdraw(@RequestBody BankWithdrawDTO BankWithdrawDTO){
        try {
            return  new ResponseDTO<>(bankWithdrawService.addwithdraw(BankWithdrawDTO))  ;
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseDTO<>( BankWithdrawDTO +" Withdraw Is Added Successfully");
    }




}
