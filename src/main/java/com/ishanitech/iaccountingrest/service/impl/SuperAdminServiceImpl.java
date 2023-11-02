package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CreditNoteDAO;
import com.ishanitech.iaccountingrest.dao.SuperAdminDAO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.SuperAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public List<UserConfigurationDTO> fetchLimitedUsersForSuperAdminListing(Integer offset, Integer pageTotalItems, String searchInput) {
        SuperAdminDAO superAdminDAO = dbService.getDao(SuperAdminDAO.class);
        String caseQuery = "";
        if (searchInput.matches("\\d+")) {
            caseQuery = "role.role <> 'SUPER_ADMIN'" + " and phone=" + searchInput + " order by users.id desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        else if (searchInput.matches("[a-zA-Z]+")) {
            caseQuery = "role.role <> 'SUPER_ADMIN'" + " and users.email like '"  + searchInput + "%' "
                    + " order by users.id" + " desc "
                    + "limit " + pageTotalItems + " offset " + (offset - 1);
        }else{
            caseQuery = "role.role <> 'SUPER_ADMIN' order by users.id desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"")){
            caseQuery = "role.role <> 'SUPER_ADMIN' order by users.id desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        return superAdminDAO.fetchLimitedUsersForSuperAdminList(caseQuery);
    }
}
