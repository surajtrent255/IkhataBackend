package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.BankDepositeDTO;
import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
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

        return new ResponseDTO<>( BankWithdrawDTO.getWithdrawId() +" Withdraw mighted be  Added Successfully");
    }


    @PutMapping
    public int updatewithdraw(@RequestBody BankWithdrawDTO bankWithdrawDTO){
        try{

            bankWithdrawService.updatewithdraw(bankWithdrawDTO);
            return 1;
        } catch (Exception e){
            log.error("error while updating deposit it " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating deposit");

        }

    }

    @DeleteMapping("/{branchId}/{withdrawId}")
    public ResponseDTO<?> deletewithdraw(@PathVariable("branchId") int branchId,@PathVariable("withdrawId") int withdrawId){
            System.out.println("branchid" + branchId+"withdreadid"+withdrawId);
            int deleted=0;
        try{

            deleted=bankWithdrawService.deletewithdraw(branchId , withdrawId);
            return  new ResponseDTO<>("Successfully Deleted" + deleted);
        }catch (Exception e){
            log.error(e.getMessage());
            return  new ResponseDTO<>("Not DEleted" );
        }


    }



}
