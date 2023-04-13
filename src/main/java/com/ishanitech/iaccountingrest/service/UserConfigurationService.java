package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.model.User;

import java.util.List;

public interface UserConfigurationService {

public void updateUserRoleStatus(boolean status,int userId,int companyId,int roleId);

public void updateUserCompanyStatus(boolean status,int userId);

public int addUserRole(int userId,int companyId,int roleId);


    List<UserConfigDTO> getAllUser();

    void AssignCompanyToUser(int companyId,int userId);

    List<UserConfigDTO> getAllUsersByCompanyId(int companyId);

    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyId(int companyId);

    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyIdAndUserId(int companyId,int userId);




}
