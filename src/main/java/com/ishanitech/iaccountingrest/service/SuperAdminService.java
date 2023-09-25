package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;

import java.util.List;

public interface SuperAdminService {
    List<CompanyDTO> getCompanyDetailsOfUserForSuperAdmin(int userId);

    void allowDisallowUserToProceedBySuperAdmin(int companyId,Boolean status);

    List<UserConfigurationDTO> fetchLimitedUsersForSuperAdminListing(Integer offset, Integer pageTotalItems, String searchInput, String searchValue);


}
