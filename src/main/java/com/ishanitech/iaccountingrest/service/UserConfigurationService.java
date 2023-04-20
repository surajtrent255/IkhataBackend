package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;

import java.util.List;

public interface UserConfigurationService {

public void updateUserRoleStatus(boolean status,int userId,int companyId,int roleId);

public void updateUserCompanyStatus(boolean status,int userId);

public void addUserRole(int userId,int companyId,int[] roleId);

public void addMultipleUserRole(int[] userId,int companyId,int roleId);


    List<UserConfigDTO> getAllUser(int companyId);

    void AssignCompanyToUser(int companyId,int[] userId);

    List<UserConfigDTO> getAllUsersByCompanyId(int companyId);

    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyId(int companyId);

    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyIdAndUserId(int companyId,int userId);




}
