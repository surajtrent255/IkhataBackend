package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.*;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import com.ishanitech.iaccountingrest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/userconfig")
@RequiredArgsConstructor
public class UserConfigurationController {

    private final UserService userService;

    private final UserConfigurationService userConfigurationService;

    @GetMapping("/{companyId}")
    public ResponseDTO<?> getUserRoleDetailsBasedOnCompanyId(@PathVariable("companyId") int companyId ){
        return new ResponseDTO<>(userConfigurationService.getUserRoleDetailsBasedOnCompanyId(companyId));
    }

    @GetMapping("/{companyId}/{userId}")
    public ResponseDTO<?> getUserRoleDetailsBasedOnCompanyIdAndUserId(@PathVariable("companyId") int companyId,@PathVariable("userId") int userId){
        try {
            return new ResponseDTO<>(userConfigurationService.getUserRoleDetailsBasedOnCompanyIdAndUserId(companyId,userId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

    @GetMapping("/role")
    public ResponseDTO<?> getAllRoles(){
        return new ResponseDTO<>(userService.getAllRole());
    }


    @PostMapping("/update/role/status")
    public String updateUserRoleStatus(@RequestParam("status") boolean status,@RequestParam("userId") int userId,@RequestParam("companyId") int companyId,@RequestParam("roleId") int roleId){
        userConfigurationService.updateUserRoleStatus(status,userId,companyId,roleId);
        return "status updated successfully";

    }

    @PostMapping("/update/usercompany/status")
    public String updateUserCompanyStatus(@RequestParam("status") boolean status,@RequestParam("userId") int userId){
        userConfigurationService.updateUserCompanyStatus(status,userId);
        return "User Company status updated successfully";

    }



    @PostMapping("/add/role")
    public ResponseDTO<?> addUserRole(@RequestParam("userId") int userId,@RequestParam("companyId") int companyId,@RequestParam("roleId") int[] roleId){
        try {
            userConfigurationService.addUserRole(userId,companyId,roleId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("Error adding Role to user");
        }
        return new ResponseDTO<>("Successfully Added Role To User");
    }

    @PostMapping("/add/multiple/user/role")
    public ResponseDTO<?> addMultipleUserRole(@RequestParam("userId") int[] userId,@RequestParam("companyId") int companyId,@RequestParam("roleId") int roleId){
        try {
            userConfigurationService.addMultipleUserRole(userId,companyId,roleId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("Error adding Role to Multiple user");
        }
        return new ResponseDTO<>("Successfully Added Role To Multiple User");
    }

    @GetMapping("/users")
    public ResponseDTO<?> getAllUsers(@RequestParam("companyId") int companyId){
        return new ResponseDTO<>(userConfigurationService.getAllUser(companyId)) ;
    }

    @PostMapping("/assign/user")
    public ResponseDTO<?> assignCompanyToUser(@RequestParam("companyId") int companyId,@RequestParam("userId") int[] userId){
        try{
           userConfigurationService.AssignCompanyToUser(companyId,userId);
            return new ResponseDTO<>("Successfully assigned");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("Error assigning company to user");
        }

    }

    @GetMapping("/users/ByCompanyId")
    public ResponseDTO<?> getAllUsersByCompanyId(@RequestParam("companyId") int companyId){
        try {
            return new ResponseDTO<>(userConfigurationService.getAllUsersByCompanyId(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

}
