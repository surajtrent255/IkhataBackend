package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CounterDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/counter")
public class CounterController {

    private final CounterService counterService;

    @GetMapping("/{companyId}")
    public ResponseDTO<?> getAllCounter(@PathVariable("companyId") int companyId){
        try{
          return new ResponseDTO<>(counterService.getAllCounterDetails(companyId)) ;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PostMapping
    public ResponseDTO<?> addCounterDetails(@RequestBody CounterDTO counterDTO){
        try{
            return new ResponseDTO<>("Data SuccessFully Added" +counterService.addCounter(counterDTO));

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseDTO<?> enableDisableCounter(@RequestParam("status") boolean status,@RequestParam("id") int id){
        try {
            return new ResponseDTO<>("Successfully Status Changed" + counterService.enableDisableCounter(status,id));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }


    }

    @GetMapping("/get/users")
    public ResponseDTO<?> getUsersForAssignCounter(@RequestParam("companyId") int companyId,@RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<>(counterService.getUsersForAssignCounter(companyId,branchId));
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PostMapping("/assign/users")
    public ResponseDTO<?> assignCounterToUsers(@RequestBody CounterDTO counterDTO){
        try {
            return  new ResponseDTO<>("User Successfully Assigned To Counter" + counterService.assignUsersToCounter(counterDTO));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/get/users/forListing/{companyId}")
    public ResponseDTO<?> getUsersForCounterListing(@PathVariable("companyId") int companyId){
        try{
            return new ResponseDTO<>(counterService.getUsersForCounterListing(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping("/update/user/counter/status")
    public ResponseDTO<?> updateUserStatusInCounter(@RequestParam("status") boolean status,@RequestParam("userId") int userId,@RequestParam("counterId") int counterId){
        try {
            counterService.updateUserStatusInCounter(status,userId,counterId);
            return new ResponseDTO<>("Status Updated Successfully");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/for/localStorage")
    public ResponseDTO<?> getUserCounterDetailsForLocalStorage(@RequestParam("companyId") int companyId,@RequestParam("userId") int userId){
        try{
            return new ResponseDTO<>(counterService.getUserCounterDetailsForLocalStorage(companyId,userId));
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }


}
