package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CreditNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
