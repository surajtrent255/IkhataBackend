package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;

import java.util.List;

public interface UserConfigurationService {

public void updateUserStatus(boolean status,int userId);

public int addUserRole(int userId,int roleId);

public void updateUserCompany(int companyId,int userId);


}
