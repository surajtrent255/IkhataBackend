package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.UserCommonConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;

import java.util.List;
import java.util.Optional;

public interface UserConfigurationService {

public void updateUserRoleStatus(boolean status,int userId,int companyId,int roleId);

public void updateUserCompanyStatus(boolean status,int userId);

public void addUserRole(int userId,int companyId,int[] roleId);

public void addMultipleUserRole(int[] userId,int companyId,int roleId);


    List<UserCommonConfigDTO> getAllUser(int companyId,Integer offset,Integer pageTotalItems,String searchInput);

    void AssignCompanyToUser(int companyId,int[] userId);

    List<UserCommonConfigDTO> getAllUsersByCompanyId(int companyId);

    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyId(int companyId);

    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyIdAndUserId(int companyId,int userId);

//    For Super Admin Users Listing And Operation


    void assignAdminRoleFromSuperAdmin(int userId,int roleId);


    List<UserCommonConfigDTO> getLimitedUsersForSearchInUserConfiguration(Integer offset,Integer pageTotalItems, int companyId,String searchInput);

    List<UserConfigurationDTO> getLimitedUsersRoleForSearchInUserConfiguration(Integer offset,Integer pageTotalItems, int companyId,String searchInput);


}
