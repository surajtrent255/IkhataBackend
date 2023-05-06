package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.BranchDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.UserBranchDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping
    public ResponseDTO<?> getAllBranchByCompanyId(@RequestParam("companyId") int companyId){
        try{
            return new ResponseDTO<>(branchService.getBranchByCompanyId(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PostMapping
    public ResponseDTO<?> addBranch(@RequestBody BranchDTO branchDTO){
        try {
            branchService.addBranch(branchDTO);
            return new ResponseDTO<>("branch Successfully Added" );

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PostMapping("/assign")
    public ResponseDTO<?> AssignBranchToUser(@RequestBody UserBranchDTO userBranchDTO){
        try {
            branchService.AssignBranchToUser(userBranchDTO);
            return new ResponseDTO<>(userBranchDTO.getUserId() + "Is Added Successfully");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @GetMapping("/get")
    public ResponseDTO<?> getBranchDetailsByCompanyAndUserId(@RequestParam("companyId") int companyId,@RequestParam("userId") int userId){
        try {
            return new ResponseDTO<>(branchService.getBranchDetailsBasedOnCompanyAndUserId(companyId,userId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @GetMapping("/users")
    public ResponseDTO<?> getBranchUsersByCompanyId(@RequestParam("companyId") int companyId){
        try {
            return new ResponseDTO<>(branchService.getBranchUserByCompanyId(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PutMapping("/enable")
    void enableDisableBranchUser(@RequestParam boolean status,@RequestParam int userId,@RequestParam int companyId,@RequestParam int branchId){
        try {
            branchService.enableDisableBranchUser(status,userId,companyId,branchId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }


    @GetMapping("/list/assign")
    public ResponseDTO<?> getUserForAssignBranchList(@RequestParam int companyId){
        try {
           return new ResponseDTO<>(branchService.getUserForAssignBranchList(companyId)) ;
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

}
