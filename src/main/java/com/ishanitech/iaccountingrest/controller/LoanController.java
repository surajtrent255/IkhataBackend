package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.LoanDTO;
import com.ishanitech.iaccountingrest.dto.LoanNamesDTO;
import com.ishanitech.iaccountingrest.dto.LoanTypesDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.LoanService;
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
    @GetMapping("/limited")
    public ResponseDTO<List<LoanDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchWildCard") String searchWildCard,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId){
        try{
            return new ResponseDTO<List<LoanDTO>>(loanService.getLimitedLoanEntitiesForSingleCompAndBranch(offset, pageTotalItems, searchBy, searchWildCard, sortBy, compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the loan infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the loan infos : " );
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

    @GetMapping("/name/repay")
    public ResponseDTO<String> getLoanNameForLoanRepay(@RequestParam("Id") int Id,
                                                       @RequestParam("companyId") int companyId,
                                                       @RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<>(loanService.getLoanNameForLoanRepay(Id,companyId,branchId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}







