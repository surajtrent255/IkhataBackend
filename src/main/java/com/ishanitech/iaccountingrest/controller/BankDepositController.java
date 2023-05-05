package com.ishanitech.iaccountingrest.controller;
import com.ishanitech.iaccountingrest.dto.BankDepositDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BankDepositeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/bank/deposite")
@RequiredArgsConstructor
public class BankDepositController {
    private final BankDepositeService BankDepositeService;
    @GetMapping
    public  ResponseDTO<?> getAllByBankBankDeposit(@RequestParam("companyId") int companyId ,@RequestParam("branchId")  int branchId){

        try{
            return new ResponseDTO<>(BankDepositeService.getAllByBankBankDeposite(companyId ,branchId));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(BankDepositeService.getAllByBankBankDeposite(companyId,branchId));
    }
//    @PostMapping
//    public ResponseDTO<?> addBankDeposite(@RequestBody BankDepositeDTO bankDepositeDTO) {
//        try {
//            return  new ResponseDTO<>(BankDepositeService.addBankDeposite(bankDepositeDTO))  ;
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//
//        return new ResponseDTO<>( bankDepositeDTO.getBankId()+" deposite Is Added Successfully");
//    }
@PostMapping
public ResponseDTO<?> addBankDeposit(@RequestBody BankDepositDTO bankDepositDTO){

        return  new ResponseDTO<>(BankDepositeService.addBankDeposit(bankDepositDTO))  ;

}



    @PutMapping
    public int updateDeposite(@RequestBody BankDepositDTO bankDepositDTO){
        try{
            BankDepositeService.updateDeposite(bankDepositDTO);
            return 1;
        } catch (Exception e){
            log.error("error while updating deposite it " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating deposite");

        }

    }
        @DeleteMapping("/{branchId}/{depositId}")
    public ResponseDTO<?> deleteFromBankDepositeBranchId(@PathVariable("branchId") int branchId , @PathVariable("depositId") int depositId){

        try {

            BankDepositeService.deleteFromBankDepositeBranchId(branchId ,depositId);

        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  new ResponseDTO<>("Successfully Deleted" );
    }





}
