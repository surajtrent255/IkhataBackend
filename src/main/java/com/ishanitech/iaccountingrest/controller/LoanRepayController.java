package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.LoanRepayDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.LoanRepayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/loan/repay")
@RequiredArgsConstructor
public class LoanRepayController {

    private final LoanRepayService loanRepayService;

    @PostMapping
    public ResponseDTO<?> addLoanRepay(@RequestBody LoanRepayDTO loanRepayDTO){
        try{
            loanRepayService.addLoanRepayDetails(loanRepayDTO);
            return new ResponseDTO<>("Data Successfully Added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseDTO<List<LoanRepayDTO>> getLoanRepay(@RequestParam("companyId") int companyId,
                                                        @RequestParam("branchId") int branchId){
        try{
           return new ResponseDTO<>(loanRepayService.getLoanRepayDetails(companyId,branchId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO<LoanRepayDTO> getSingleLoanRepay(@RequestParam("companyId") int companyId,
                                                        @RequestParam("branchId") int branchId,
                                                        @PathVariable("id") int id){
        try{
            return new ResponseDTO<>(loanRepayService.getSingleLoanRepayDetails(companyId,branchId,id));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping
    public ResponseDTO<?> updateLoanRepay(@RequestBody LoanRepayDTO loanRepayDTO){
        try{
            loanRepayService.updateLoanRepay(loanRepayDTO);
            return new ResponseDTO<>("Data Successfully Updated");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseDTO<?> deleteLoanRepay(@RequestParam("id") int id){
        try{
            loanRepayService.deleteLoanRepay(id);
            return new ResponseDTO<>("Data Successfully deleted");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
