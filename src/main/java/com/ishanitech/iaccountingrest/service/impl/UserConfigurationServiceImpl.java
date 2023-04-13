package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.UserConfigutarionDAO;
import com.ishanitech.iaccountingrest.dao.UserDAO;
import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.model.User;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int addUserRole(int userId,int companyId, int roleId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);

        int savedCompanyId =0;
        try{
            savedCompanyId = userConfigutarionDAO.addRoleToUser(userId,companyId,roleId);
        }catch (JdbiException jdbiException){
            log.error(jdbiException.getMessage());
        }

        return savedCompanyId;

    }





    @Override
    public List<UserConfigDTO> getAllUser() {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return  userConfigutarionDAO.getAllUser();
    }

    @Override
    public void AssignCompanyToUser(int companyId, int userId) {
        UserConfigutarionDAO userConfigutarionDAO =dbService.getDao(UserConfigutarionDAO.class);
        userConfigutarionDAO.AssignCompanyToUser(companyId,userId);

    }

    @Override
    public List<UserConfigDTO> getAllUsersByCompanyId(int companyId) {
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
}
