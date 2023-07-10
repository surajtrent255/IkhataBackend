package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/superAdmin")
public class SuperAdminController {

    private final SuperAdminService superAdminService;

    @GetMapping
    public ResponseDTO<?> getCompanyDetailsOfUserForSuperAdmin(@RequestParam("userId") int userId){
        try{
            return new ResponseDTO<>(superAdminService.getCompanyDetailsOfUserForSuperAdmin(userId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping
    public ResponseDTO<?> allowDisallowUserToProceedBySuperAdmin(@RequestParam("companyId") int companyId,
                                                                 @RequestParam("status") Boolean status)
    {
        try {
            superAdminService.allowDisallowUserToProceedBySuperAdmin(companyId,status);
            return new ResponseDTO<>("Permission Granted");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }


}
