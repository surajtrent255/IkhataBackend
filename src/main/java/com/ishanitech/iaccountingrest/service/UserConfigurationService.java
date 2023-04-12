package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.model.User;

import java.util.List;

public interface UserConfigurationService {

public void updateUserStatus(boolean status,int userId);

public void updateUserCompanyStatus(boolean status,int userId);

public int addUserRole(int userId,int roleId);

public void updateUserRoleCompany(int companyId,int userId);

public void updateUserRole(int userId);

    List<UserConfigDTO> getAllUser();

    void AssignCompanyToUser(int companyId,int userId);

    List<UserConfigDTO> getAllUsersByCompanyId(int companyId);




}
