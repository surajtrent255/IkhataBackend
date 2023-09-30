package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.*;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.model.User;
import com.ishanitech.iaccountingrest.service.CompanyService;
import com.ishanitech.iaccountingrest.service.UserCompanyService;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import com.ishanitech.iaccountingrest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/userconfig")
@RequiredArgsConstructor
public class UserConfigurationController {

    private final UserService userService;

    private final UserConfigurationService userConfigurationService;

    private final UserCompanyService userCompanyService;

    private final CompanyService companyService;

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
            throw new CustomSqlException(e.getMessage());
        }

    }

    @GetMapping("/role")
    public ResponseDTO<?> getAllRoles(){

        try {
            return new ResponseDTO<>(userService.getAllRole());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }


    @PostMapping("/update/role/status")
    public ResponseDTO<?> updateUserRoleStatus(@RequestParam("status") boolean status,@RequestParam("userId") int userId,@RequestParam("companyId") int companyId,@RequestParam("roleId") int roleId){
        try{
            userConfigurationService.updateUserRoleStatus(status,userId,companyId,roleId);
            return new ResponseDTO<>("status updated successfully") ;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }


    }

    @PostMapping("/update/usercompany/status")
    public ResponseDTO<?> updateUserCompanyStatus(@RequestParam("status") boolean status,@RequestParam("userId") int userId){
        try{
            userConfigurationService.updateUserCompanyStatus(status,userId);
            return new ResponseDTO<>("User Company status updated successfully");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }


    }



    @PostMapping("/add/role")
    public ResponseDTO<?> addUserRole(@RequestParam("userId") int userId,@RequestParam("companyId") int companyId,@RequestParam("roleId") int[] roleId){
        try {
            userConfigurationService.addUserRole(userId,companyId,roleId);
            return new ResponseDTO<>("Successfully Added Role To User");

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("Error adding Role to user");
        }
    }

    @PostMapping("/add/multiple/user/role")
    public ResponseDTO<?> addMultipleUserRole(@RequestParam("userId") int[] userId,@RequestParam("companyId") int companyId,@RequestParam("roleId") int roleId){
        try {
            userConfigurationService.addMultipleUserRole(userId,companyId,roleId);
            return new ResponseDTO<>("Successfully Added Role To Multiple User");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("Error adding Role to Multiple user");
        }
    }

    @GetMapping("/users")
    public ResponseDTO<?> getAllUsers(@RequestParam("companyId") int companyId, @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
                                      @RequestParam("searchInput") String searchInput){
        try{
            return new ResponseDTO<>(userConfigurationService.getAllUser(companyId,offset,pageTotalItems,searchInput)) ;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
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
            throw new CustomSqlException(e.getMessage());
        }

    }


//    For Super Admin
    @GetMapping("/superAdmin")
    public ResponseDTO<?> getAllUsersForSuperAdminListing(){
        try{
            return new ResponseDTO<>(userConfigurationService.getAllUsersForSuperAdminListing());

        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PutMapping("/superAdmin/assign")
    public ResponseDTO<?> assignAdminRoleToUserFromSuperAdmin(@RequestParam("userId") int userId){
        try{

            userConfigurationService.assignAdminRoleFromSuperAdmin(userId,1);
            List<CompanyDTO> companyDTO = userCompanyService.getCompanyDetailsFromUserCompanyRoleTableForDisableCompany(userId);
            for (CompanyDTO company : companyDTO) {
                companyService.updateCompanyStatus(true, company.getCompanyId());
            }
            return new ResponseDTO<>("Assign Successful");


        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping("/superAdmin")
    public ResponseDTO<?> enableDisableUsersFromSuperAdmin(@RequestParam("status") boolean status,@RequestParam("userId") int userId){
        try{
            boolean UserStatus = status;
            if(UserStatus == false) {
                userConfigurationService.assignAdminRoleFromSuperAdmin(userId,2);
                List<CompanyDTO> companyDTO =userCompanyService.getCompanyDetailsFromUserCompanyRoleTableForDisableCompany(userId);
                for (CompanyDTO company : companyDTO) {
                    companyService.updateCompanyStatus(false, company.getCompanyId());
                }
            }
            return new ResponseDTO<>("Users Status Update Successful");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }


    @GetMapping("/search/users")
    public  ResponseDTO<?> getLimitedUsersForSearchInUserConfiguration( @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
                                                                        @RequestParam("compId") Integer compId,@RequestParam("searchInput") String searchInput )
    {
        try{
            return new ResponseDTO<>(userConfigurationService.getLimitedUsersForSearchInUserConfiguration(offset,pageTotalItems,compId,searchInput)) ;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/search/users/role")
    public  ResponseDTO<?> getLimitedUsersRoleForSearchInUserConfiguration( @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
                                                                        @RequestParam("compId") Integer compId,@RequestParam("searchInput") String searchInput )
    {
        try{
            return new ResponseDTO<>(userConfigurationService.getLimitedUsersRoleForSearchInUserConfiguration(offset,pageTotalItems,compId,searchInput)) ;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

}
