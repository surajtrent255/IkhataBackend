package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DebitNoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/details")
    public ResponseDTO<?> addDebitNoteDetails(@RequestBody DebitNoteDetailsDTO debitNoteDetailsDTO){
        try{
            debitNoteService.addDebitNoteDetails(debitNoteDetailsDTO);
            return new ResponseDTO<>("Data Added Successfully");
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseDTO<?> getDebitNoteInfo(@RequestParam("companyId") int companyId,@RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<>(debitNoteService.getDebitNoteInfo(companyId,branchId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/limited")
    public ResponseDTO<List<DebitNoteDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId){
        try{
            return new ResponseDTO<List<DebitNoteDTO>>(debitNoteService.getLimitedDebitNotessByCompIdAndBranchId(offset, pageTotalItems, compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
    }

    @GetMapping("/details")
    public ResponseDTO<?> getDebitNoteDetailsInfo(@RequestParam("billNumber") long billNumber){
        try{
            return new ResponseDTO<>(debitNoteService.getDebitNoteDetailsInfo(billNumber));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
