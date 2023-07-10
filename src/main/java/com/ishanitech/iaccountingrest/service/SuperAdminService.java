package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;

import java.util.List;

public interface SuperAdminService {
    List<CompanyDTO> getCompanyDetailsOfUserForSuperAdmin(int userId);

    void allowDisallowUserToProceedBySuperAdmin(int companyId,Boolean status);


}
