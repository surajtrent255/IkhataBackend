package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DebitNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ap1/v1/debitNote")
public class DebitNoteController {

    private final DebitNoteService debitNoteService;

    @PostMapping
    public ResponseDTO<?> addDebitNote(@RequestBody DebitNoteDTO debitNoteDTO){
        try{
            debitNoteService.addDebitNote(debitNoteDTO);
            return  new ResponseDTO<>("Successfully Added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
