package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.BankWithdrawDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BankWithdrawService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @GetMapping("/single")
    public Mono<ResponseDTO<BankWithdrawDTO>> getSingleBankWithDraw(
            @RequestParam("id") Integer id,
            @RequestParam("compId") Integer companyId,
            @RequestParam("branchId") Integer branchId
    ){
        return Mono.fromCallable(()-> new ResponseDTO<BankWithdrawDTO>(bankWithdrawService.getSingleBankWithdraw(id, companyId, branchId)))
                .onErrorResume(throwable -> {
                    log.error(" went wrong in getting single Bank WithDraw " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("Somethiing went wrong while fetching BankWithDrawInfo"));
                });
    }
    @GetMapping("/limited")
    public ResponseDTO<List<BankWithdrawDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId){
        try{
            return new ResponseDTO<List<BankWithdrawDTO>>(bankWithdrawService.getLimitedBanksWithdrawByCompIdAndBranchId(offset, pageTotalItems, compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
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
    public ResponseDTO<Integer> updatewithdraw(@RequestBody BankWithdrawDTO bankWithdrawDTO){
        try{
            bankWithdrawService.updatewithdraw(bankWithdrawDTO);
            return new ResponseDTO<>(1);
        } catch (Exception e){
            log.error("error while updating deposit it " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating deposit");

        }

    }

        @DeleteMapping("/{branchId}/{withdrawId}")
    public ResponseDTO<?> deletewithdraw(@PathVariable("branchId") int branchId,@PathVariable("withdrawId") int withdrawId){
            System.out.println("branchid" + branchId+"withdrew"+withdrawId);
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
