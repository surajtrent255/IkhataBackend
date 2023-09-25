package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.UserConfigutarionDAO;
import com.ishanitech.iaccountingrest.dto.UserCommonConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConfigurationServiceImpl implements UserConfigurationService {

    private final DbService dbService;

    @Override
    public void updateUserRoleStatus(boolean status, int userId,int companyId,int roleId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        userConfigutarionDAO.updateUserRoleStatus(status,userId,companyId,roleId);
    }

    @Override
    public void updateUserCompanyStatus(boolean status, int userId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        userConfigutarionDAO.updateUserCompanyStatus(status,userId);
    }

    @Override
    public void addUserRole(int userId,int companyId, int [] roleId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        for(int RoleId: roleId){
            userConfigutarionDAO.addRoleToUser(userId,companyId,RoleId);
        }
    }

    @Override
    public void addMultipleUserRole(int[] userId, int companyId, int roleId) {

        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);

        for (int newUserId : userId) {
            userConfigutarionDAO.addRoleToUser(newUserId, companyId, roleId);
        }
    }


    @Override
    public List<UserCommonConfigDTO> getAllUser(int companyId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return  userConfigutarionDAO.getAllUser(companyId);
    }

    @Override
    public void AssignCompanyToUser(int companyId, int[] userId) {
        UserConfigutarionDAO userConfigutarionDAO =dbService.getDao(UserConfigutarionDAO.class);

        for (int newUserId : userId) {
            userConfigutarionDAO.AssignCompanyToUser(companyId, newUserId);
        }
    }

    @Override
    public List<UserCommonConfigDTO> getAllUsersByCompanyId(int companyId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return userConfigutarionDAO.getUsersByCompanyId(companyId);
    }

    @Override
    public List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyId(int companyId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return userConfigutarionDAO.getUserRoleDetailsBasedOnCompanyId(companyId);
    }

    @Override
    public List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyIdAndUserId(int companyId, int userId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return userConfigutarionDAO.getUserRoleDetailsBasedOnCompanyIdAndUserId(companyId,userId);
    }

    @Override
    public List<UserConfigurationDTO> getAllUsersForSuperAdminListing() {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return userConfigutarionDAO.getAllUsersForSuperAdminList();
    }

    @Override
    public void assignAdminRoleFromSuperAdmin(int userId,int roleId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        userConfigutarionDAO.assignAdminRoleToUserBySuperAdmin(userId, roleId);


    }

    @Override
    public List<UserCommonConfigDTO> getLimitedUsersForSearchInUserConfiguration(Integer offset, Integer pageTotalItems, int companyId,String searchInput) {
        String caseQuery = "";
        if(searchInput.length() !=0 )
            caseQuery = " user_company.company_id = " + companyId + " and " + "users.email like '"  + searchInput + "%' order by " + "users.id" + " desc " +
                    "limit " + pageTotalItems + " offset " + (offset - 1);
        else {
            caseQuery = " user_company.company_id = "+companyId+" order by "+"users.id"+" desc "+
                    "limit "+ pageTotalItems+" offset "+(offset-1);
        }

        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return userConfigutarionDAO.getLimitedUsersForSearchInUserConfiguration(caseQuery);
    }

    @Override
    public List<UserConfigurationDTO> getLimitedUsersRoleForSearchInUserConfiguration(Integer offset, Integer pageTotalItems, int companyId, String searchInput) {
        String caseQuery = "";
        if (searchInput.length() != 0) {
            caseQuery = " user_company_role.company_id = " + companyId + " AND user_company_role.status = true AND users.email like '" + searchInput + "%' order by users.id desc " +
                    "limit " + pageTotalItems + " offset " + (offset - 1);
        } else {
            caseQuery = " user_company_role.company_id = " + companyId + " AND user_company_role.status = true order by users.id desc " +
                    "limit " + pageTotalItems + " offset " + (offset - 1);
        }

        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return userConfigutarionDAO.getLimitedUserRoleForSearch(caseQuery);
    }


}
