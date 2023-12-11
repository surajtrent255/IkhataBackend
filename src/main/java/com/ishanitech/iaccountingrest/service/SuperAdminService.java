package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface SuperAdminService {
    List<CompanyDTO> getCompanyDetailsOfUserForSuperAdmin(int userId);

    void allowDisallowUserToProceedBySuperAdmin(int companyId,Boolean status);

    List<UserConfigurationDTO> fetchLimitedUsersForSuperAdminListing(Integer offset, Integer pageTotalItems, String searchInput);

    List<CompanyDTO> fetchListOfCompanyWithNoUser(HttpServletRequest request);

    Integer AssignUserWithNoCompany(int companyId, int userId);



}
