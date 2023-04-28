package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.LoanDTO;
import com.ishanitech.iaccountingrest.dto.LoanNamesDTO;
import com.ishanitech.iaccountingrest.dto.LoanTypesDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.LoanService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
@Slf4j
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseDTO<Integer> createNewLoan(@RequestBody LoanDTO loanDTO){
        try{
            return new ResponseDTO<Integer>(loanService.createNewLoan(loanDTO));
        } catch(Exception ex){
            log.error("creating loan "+ex.getMessage());
            throw new CustomSqlException("Something went wrong while creating loan !!!");
        }
    }

    @GetMapping("/all")
    public ResponseDTO<List<LoanDTO>> getAllLoanEntitiesForSingleCompAndBranch(@RequestParam("compId") int compId, @RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<List<LoanDTO>>(loanService.getAllLoanEntitiesForSingleCompAndBranch(compId, branchId));
        } catch(Exception ex){
            log.error("fetching loan sing " + ex.getMessage());
            throw new CustomSqlException("Something went wrong while fetching loans");
        }
    }

    @GetMapping
    public ResponseDTO<LoanDTO> getSingleLoanEntity(@RequestParam("id") int id, @RequestParam("compId") int compId, @RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<LoanDTO>(loanService.getSingleLoanEntity(compId, branchId, id));
        } catch(Exception ex){
            log.error("fetching single loan "+ex.getMessage());
            throw new CustomSqlException("Something went wrong while fetching loan");
        }
    }

    @PostMapping("/{id}")
    public void updateTheLoan(@RequestParam("compId") int compId,
                              @RequestParam("branchId") int branchId,
                              @PathVariable("id") int id,
                              @RequestBody LoanDTO loanDTO){
        try{
             loanService.updateTheLoan(compId, branchId, id, loanDTO);
        } catch(Exception ex){
            log.error("updating the loan "+ ex.getMessage());
            throw new CustomSqlException("Something went wrong while updating loan");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTheLoan(@PathVariable("id") int id,
                              @RequestParam("compId") int compId,
                              @RequestParam("branchId") int branchId){
        try{
            loanService.deleteTheLoanEntity( compId, branchId, id);
        } catch(Exception ex){
            log.error("deleting the loan " + ex.getMessage());
            throw new CustomSqlException("Something went wrong while deleting loan. ");
        }
    }

    @GetMapping("/types")
    public ResponseDTO<List<LoanTypesDTO>> getALlLoanTypes(){
        try{
            return new ResponseDTO<List<LoanTypesDTO>>(loanService.getAllLoanTypes());
        }catch(Exception ex){
            log.error("fetching loan types " + ex.getMessage());
            throw new CustomSqlException("Something went wrong while fetching loan types");
        }
    }
    @GetMapping("/names")
    public ResponseDTO<List<LoanNamesDTO>> getALlLoanNames(){
        try{
            return new ResponseDTO<List<LoanNamesDTO>>(loanService.getAllLoanNames());
        }catch(Exception ex){
            log.error("fetching loan name " + ex.getMessage());
            throw new CustomSqlException("Something went wrong while fetching loan name");
        }
    }
}







