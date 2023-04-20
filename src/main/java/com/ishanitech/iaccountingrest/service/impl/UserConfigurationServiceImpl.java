package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.UserConfigutarionDAO;
import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void addUserRole(int userId,int companyId, int [] roleId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        int NewRoleId;

        for(int i=0;i<roleId.length;i++){
            NewRoleId = roleId[i];
            userConfigutarionDAO.addRoleToUser(userId,companyId,NewRoleId);
        }
    }

    @Override
    public void addMultipleUserRole(int[] userId, int companyId, int roleId) {

        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        int newUserId;
        for(int i = 0;i<userId.length;i++){
            newUserId = userId[i];
            userConfigutarionDAO.addRoleToUser(newUserId,companyId,roleId);
        }
    }


    @Override
    public List<UserConfigDTO> getAllUser(int companyId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        return  userConfigutarionDAO.getAllUser(companyId);
    }

    @Override
    public void AssignCompanyToUser(int companyId, int[] userId) {
        UserConfigutarionDAO userConfigutarionDAO =dbService.getDao(UserConfigutarionDAO.class);
        int newUserId;
        for(int i=0;i<userId.length;i++){
            newUserId = userId[i];
            userConfigutarionDAO.AssignCompanyToUser(companyId,newUserId);
        }
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
