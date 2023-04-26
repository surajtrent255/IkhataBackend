package com.ishanitech.iaccountingrest.controller;
import com.ishanitech.iaccountingrest.dto.BankDepositeDTO;
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
public class BankDepositeController {
    private final BankDepositeService BankDepositeService;
    @GetMapping
    public  ResponseDTO<?> getAllByBankBankDeposite(@RequestParam("companyId") int companyId ,@RequestParam("branchId")  int branchId){

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
public ResponseDTO<?> addBankDeposit(@RequestBody BankDepositeDTO bankDepositeDTO){
    try {
        return  new ResponseDTO<>(BankDepositeService.addBankDeposit(bankDepositeDTO))  ;
    }catch (Exception e){
        log.error(e.getMessage());
    }

    return new ResponseDTO<>( bankDepositeDTO.getBankId() +" deposite Is Added Successfully");
}



    @PutMapping
    public int updateDeposite(@RequestBody BankDepositeDTO bankDepositeDTO){
        try{
            BankDepositeService.updateDeposite(bankDepositeDTO);
            return 1;
        } catch (Exception e){
            log.error("error while updating deposite it " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating deposite");

        }

    }
    @DeleteMapping("/{branchId}/{chequeNumber}")
    public ResponseDTO<?> deleteFromBankDepositeBranchId(@PathVariable("branchId") int branchId , @PathVariable("chequeNumber") String chequeNumber){

        try {

            BankDepositeService.deleteFromBankDepositeBranchId(branchId ,chequeNumber);

        }catch (Exception e){
            log.error(e.getMessage());
        }
        return  new ResponseDTO<>("Successfully Deleted" );
    }





}
