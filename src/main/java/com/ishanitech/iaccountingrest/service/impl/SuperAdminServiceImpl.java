package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CreditNoteDAO;
import com.ishanitech.iaccountingrest.dao.SuperAdminDAO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.PaginationTypeClass;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.SuperAdminService;
import com.ishanitech.iaccountingrest.utils.CustomQueryCreator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.JdbiException;
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

    @Override
    public List<CompanyDTO> fetchListOfCompanyWithNoUser(HttpServletRequest request) {
        System.out.println(request);
        String caseQuery = CustomQueryCreator.generateQueryWithCase(request, PaginationTypeClass.SUPERADMIN,dbService);
        SuperAdminDAO superAdminDAO = dbService.getDao(SuperAdminDAO.class);
        return superAdminDAO.fetchCompanyWithNoUsers(caseQuery);
    }

    @Override
    public Integer AssignUserWithNoCompany(int companyId, int userId) {
        SuperAdminDAO superAdminDAO = dbService.getDao(SuperAdminDAO.class);
        try{
            superAdminDAO.assignUserWithNoCompany(companyId,userId);
            return 1;
        }catch (JdbiException e){
            throw new CustomSqlException(e.getMessage());
        }
    }

}
