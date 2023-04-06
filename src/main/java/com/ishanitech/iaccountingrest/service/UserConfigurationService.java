package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;

import java.util.List;

public interface UserConfigurationService {

public void updateUserStatus(boolean status,int userId);

public void updateUserCompanyStatus(boolean status,int companyId);

public int addUserRole(int userId,int roleId);

public void updateUserRoleCompany(int companyId,int userId);

public void updateUserRole(int userId);

    List<UserConfigDTO> getAllUser();




}
