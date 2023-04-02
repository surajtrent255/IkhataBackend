package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.UserConfigutarionDAO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConfigurationServiceImpl implements UserConfigurationService {

    private final DbService dbService;

    @Override
    public void updateUserStatus(boolean status, int userId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        userConfigutarionDAO.updateUserStatus(status,userId);
    }

    @Override
    public void updateUserCompanyStatus(boolean status, int companyId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        userConfigutarionDAO.updateUserCompanyStatus(status,companyId);
    }

    @Override
    public int addUserRole(int userId, int roleId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);

        int savedCompanyId =0;
        try{
            savedCompanyId = userConfigutarionDAO.addRoleToUser(userId,roleId);
        }catch (JdbiException jdbiException){
            log.error("error adding role to user");
        }

        return savedCompanyId;

    }

    @Override
    public void updateUserRoleCompany(int companyId, int userId) {
        UserConfigutarionDAO userConfigutarionDAO = dbService.getDao(UserConfigutarionDAO.class);
        userConfigutarionDAO.updateUserRoleCompany(companyId,userId);
    }
}
