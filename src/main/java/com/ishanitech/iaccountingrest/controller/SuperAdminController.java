package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.SuperAdminService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public ResponseDTO<List<UserConfigurationDTO>> fetchLimitedUsersForSuperAdminListing(@RequestParam("offset") Integer offset,
                                                                                         @RequestParam("pageTotalItems") Integer pageTotalItems,
                                                                                         @RequestParam("searchInput") String searchInput){
        try{
            return new ResponseDTO<List<UserConfigurationDTO>>(superAdminService.fetchLimitedUsersForSuperAdminListing(offset,pageTotalItems,searchInput)) ;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @GetMapping("/noUserCompany")
    public  ResponseDTO<List<CompanyDTO>> fetchAllCompaniesWithNoUser(HttpServletRequest request){
        try{
            return new ResponseDTO<List<CompanyDTO>>(superAdminService.fetchListOfCompanyWithNoUser(request)) ;

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PutMapping("/assign/company/existing/user")
    public ResponseDTO<Integer> assignUserWithNoCompany(@RequestParam("companyId") int companyId, @RequestParam("userId") int userId) {
        try {
            return new ResponseDTO<>(superAdminService.AssignUserWithNoCompany(companyId, userId));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
