package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.BranchDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.UserBranchDTO;
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
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("data");
    }

    @PostMapping
    public ResponseDTO<?> addBranch(@RequestBody BranchDTO branchDTO){
        try {
            branchService.addBranch(branchDTO);
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("branch Successfully Added" );
    }

    @PostMapping("/assign")
    public ResponseDTO<?> AssignBranchToUser(@RequestBody UserBranchDTO userBranchDTO){
        try {
            branchService.AssignBranchToUser(userBranchDTO);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseDTO<>(userBranchDTO.getUserId() + "Is Added Successfully");
    }

    @GetMapping("/get")
    public ResponseDTO<?> getBranchDetailsByCompanyAndUserId(@RequestParam("companyId") int companyId,@RequestParam("userId") int userId){
        try {
            return new ResponseDTO<>(branchService.getBranchDetailsBasedOnCompanyAndUserId(companyId,userId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

}
