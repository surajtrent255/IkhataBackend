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

    @GetMapping
    public ResponseDTO<?> getCompanyConfigurationDetails(){
        return new ResponseDTO<>(userService.getUserConfigurationDetails());
    }

    @PostMapping("/status")
    public String updateUserStatus(@RequestBody UserStatusDTO statusDTO){
        boolean status = statusDTO.isStatus();
        int userId = statusDTO.getUserId();
        userConfigurationService.updateUserStatus(status,userId);
        return "status updated successfully";

    }

    @PostMapping("/update/company")
    public ResponseDTO<?> updateUserCompany(@RequestBody UserConfigCompanyDTO userConfigCompanyDTO){
        int companyId = userConfigCompanyDTO.getCompanyId();
        int userId = userConfigCompanyDTO.getUserId();
        userConfigurationService.updateUserCompany(companyId,userId);

        return new ResponseDTO<>("User Company Successfully updated");
    }

    @PostMapping("/add/role")
    public ResponseDTO<?> addUserRole(@RequestBody UserRoleConfigDTO userRoleConfigDTO){
        int userId = userRoleConfigDTO.getUserId();
        int roleId = userRoleConfigDTO.getRoleId();
        Integer result = null;
        try {
            result = userConfigurationService.addUserRole(userId,roleId);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("Error adding Role to user");
        }

        return new ResponseDTO<>("Successfully Added Role To User");
    }

}
