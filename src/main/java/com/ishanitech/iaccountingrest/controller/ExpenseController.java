package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ExpenseDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public ResponseDTO<?> getExpenseDetails(@RequestParam("companyId") int companyId){

        try {
            return new ResponseDTO<>(expenseService.getExpenseDetails(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseDTO<>("Done" );
    }

    @GetMapping("/{SN}")
    public ResponseDTO<?> getExpenseDetailsBySN(@PathVariable("SN") int SN){
        try {
            return new ResponseDTO<>(expenseService.getExpenseDetailsBySN(SN));
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseDTO<>("Done" );
    }

    @PutMapping
    public ResponseDTO<?> updateExpenseDetails(@RequestBody ExpenseDTO expenseDTO){
        try {
            expenseService.updateExpenseDetails(expenseDTO);
            return new ResponseDTO<>("Update Successfully");
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

    @PostMapping
    public ResponseDTO<?> addExpenseDetails(@RequestBody ExpenseDTO expenseDTO){
        try {
            return new ResponseDTO<>(expenseService.addExpenseDetails(expenseDTO));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Data Successfully Added" + expenseDTO);
    }

    @DeleteMapping("/{SN}")
    public ResponseDTO<?> deleteExpenseDetails(@PathVariable("SN") int SN){
        try{
            expenseService.deleteFromExpense(SN);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Delete Successfull");
    }


}
