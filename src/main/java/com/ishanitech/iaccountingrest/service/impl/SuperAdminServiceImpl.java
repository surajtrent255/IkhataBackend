package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.SuperAdminDAO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SuperAdminServiceImpl implements SuperAdminService {
    private final DbService dbService;
    @Override
    public List<CompanyDTO> getCompanyDetailsOfUserForSuperAdmin(int userId) {
        SuperAdminDAO superAdminDAO = dbService.getDao(SuperAdminDAO.class);
        return superAdminDAO.getCompanyDetailsOfUserForSuperAdmin(userId);
    }

    @Override
    public void allowDisallowUserToProceedBySuperAdmin(int companyId,Boolean status) {
        SuperAdminDAO superAdminDAO = dbService.getDao(SuperAdminDAO.class);
        superAdminDAO.allowDisallowUserToProceedBySuperAdmin(companyId,status);
    }
}
