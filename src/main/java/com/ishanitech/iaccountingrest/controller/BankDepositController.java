package com.ishanitech.iaccountingrest.controller;
import com.ishanitech.iaccountingrest.dto.BankDepositDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BankDepositeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @GetMapping("/single")
    public Mono<ResponseDTO<BankDepositDTO>> getBankDepositEntity(
            @RequestParam("compId") Integer companyId,
            @RequestParam("branchId") Integer branchId,
            @RequestParam("id") Integer id
    ){

//        try{
//            return new ResponseDTO<BankDepositDTO>(BankDepositeService.getSingleBankDeposit(id, companyId, branchId));
//        } catch(Exception e) {
//            log.error("Error occured accessing the bill infos : " + e.getMessage());
//            throw new CustomSqlException("Error occured accessing the bill infos : " );
//        }

        return Mono.fromCallable(()->  new ResponseDTO<BankDepositDTO>(BankDepositeService.getSingleBankDeposit(id, companyId, branchId))).onErrorResume(
                throwable -> {
                log.error("error fetching single bank deposit Info " + throwable.getMessage());
                return Mono.error(new CustomSqlException(" something went wrong while fetching single bank deposit info"));
                }
        );
    }


    @GetMapping("/limited")
    public ResponseDTO<List<BankDepositDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId){
        try{
            return new ResponseDTO<List<BankDepositDTO>>(BankDepositeService.getLimitedBankDepositByCompIdAndBranchId(offset, pageTotalItems, compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
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
    public ResponseDTO<Integer> updateDeposite(@RequestBody BankDepositDTO bankDepositDTO){
        try{
            BankDepositeService.updateDeposite(bankDepositDTO);
            return new ResponseDTO<>(1);
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
