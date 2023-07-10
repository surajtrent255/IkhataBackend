package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CreditNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/creditNote")
public class CreditNoteController {

    private final CreditNoteService creditNoteService;

    @PostMapping
    public ResponseDTO<?> addCreditNote(@RequestBody CreditNoteDTO creditNoteDTO){
        try{
            creditNoteService.addCreditNote(creditNoteDTO);
            return new ResponseDTO<>("Successfully added");
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PostMapping("/details")
    public ResponseDTO<?> addCreditNoteDetails(@RequestBody CreditNoteDetailsDTO creditNoteDetailsDTO){
        try{
            creditNoteService.addCreditNoteDetails(creditNoteDetailsDTO);
            return new ResponseDTO<>("Successfully added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }


    @GetMapping
    public ResponseDTO<?> getCreditNoteInfo(@RequestParam("companyId") int companyId,@RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<>(creditNoteService.getCreditNoteInfo(companyId,branchId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/details")
    public ResponseDTO<?> getCreditNoteDetailInfo(@RequestParam("billNumber") String billNumber){
        try{
            return new ResponseDTO<>(creditNoteService.getCreditNoteDetailInfo(billNumber));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/searchBy")
    public ResponseDTO<List<CreditNoteDTO>> searchCreditNoteByAttribute(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") int compId,@RequestParam("searchInput") String searchInput,@RequestParam("searchValue") String searchValue){
        try{
            return new ResponseDTO<List<CreditNoteDTO>>(creditNoteService.searchCreditNoteBySearchInput(offset,pageTotalItems,compId,searchInput,searchValue));
        } catch(Exception e) {
            log.error("Error occurred accessing the Credit Note info : " + e.getMessage());
            throw new CustomSqlException("Error occurred accessing the Credit Note info : " );
        }
    }

}
