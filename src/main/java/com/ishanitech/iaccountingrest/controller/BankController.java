package com.ishanitech.iaccountingrest.controller;
import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.BankListDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping("/")
    public ResponseDTO<?> getAllByBank(){
        try{
            return new ResponseDTO<>(bankService.getAllByBank());
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(bankService.getAllByBank());
    }
    @GetMapping("id")
    public ResponseDTO<?> getAllByBankId(@RequestParam("id") int Id){
        try{
            return new ResponseDTO<>(bankService.getAllByBankId(Id));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
//        return new ResponseDTO<>("data");
        return new ResponseDTO<>(bankService.getAllByBankId(Id));
    }
    @GetMapping("bankname")
    public ResponseDTO<?> getAllByBankName(@RequestParam("bankname") String bankName){
        try{
            return new ResponseDTO<>(bankService.getAllByBankName(bankName));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(bankService.getAllByBankName(bankName));
    }

    @GetMapping("companyid")
    public ResponseDTO<?> getAllByBankCompany(@RequestParam("company") int companyId){
        try{
            return new ResponseDTO<>(bankService.getAllByBankCompany(companyId));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(bankService.getAllByBankCompany(companyId));
    }
    @GetMapping("branchid")
    public ResponseDTO<?> getAllByBankBranch(@RequestParam("companyid") int companyId ,@RequestParam("branchid")  int branchId){
        try{
            return new ResponseDTO<>(bankService.getAllByBankBranch(companyId ,branchId));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(bankService.getAllByBankBranch(companyId,branchId));
    }
    @PostMapping("/bank")
    public ResponseDTO<?> addbank(@RequestBody BankDTO bankDTO){

          return  new ResponseDTO<>(bankService.addbank(bankDTO))  ;
    }

    @PutMapping
    public int updateBank(@RequestBody BankDTO BankDTO){
        try{
            bankService.updateBank(BankDTO);
        } catch (Exception e){
            log.error("error while updating bankedit " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating category");
        }
        return 1;
    }

    @DeleteMapping("/{companyId}/{branchId}")
    public ResponseDTO<?> deleteFromBankByCompanyIdAndBranchId(@PathVariable("companyId") int companyId,@PathVariable("branchId") int branchId){
        try {
            bankService.deleteBankByCompanyAndBranchId(companyId,branchId);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Bank Successfullly deleted");
    }
    @DeleteMapping("/{accountNo}")
    public ResponseDTO<?> deleteFromBankByAccountNo(@PathVariable("accountNo") Long accountNo){
        int success =0;
        try{
           success = bankService.DeleteFromBankByAccountNo(accountNo);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  new ResponseDTO<>("Successfully Deleted" + success);
    }
    @GetMapping("/list")
    public ResponseDTO<?> getAllBankList(){
        try{
            List<BankListDTO> b = bankService.getAllBankList();
            return new ResponseDTO<>(bankService.getAllBankList());
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
       return  new ResponseDTO<>(bankService.getAllBankList());
    }
    @GetMapping("/payment")
    public ResponseDTO<?> getAllPayment(){
        try{
            return new ResponseDTO<>(bankService.getAllPayment());
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return  new ResponseDTO<>(bankService.getAllPayment());
    }
    @GetMapping("/accounttype")
    public ResponseDTO<?> getAccountType(){
        try{
            return new ResponseDTO<>(bankService.getAccountType());
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return  new ResponseDTO<>(bankService.getAccountType());
    }
}
