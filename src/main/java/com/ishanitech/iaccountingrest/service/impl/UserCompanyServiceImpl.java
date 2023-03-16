package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.UserCompanyDAO;
import com.ishanitech.iaccountingrest.dto.UserCompanyDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UserCompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCompanyServiceImpl implements UserCompanyService {

    private final DbService dbService;

    public UserCompanyServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }


    @Override
    public Integer addUserCompany(UserCompanyDTO userCompanyDTO) {
        UserCompanyDAO userCompanyDAO = dbService.getDao(UserCompanyDAO.class);

        return userCompanyDAO.addUserCompany(userCompanyDTO);
    }

    @Override
    public void deleteUserCompany(Integer id) {
        UserCompanyDAO userCompanyDAO = dbService.getDao(UserCompanyDAO.class);

        userCompanyDAO.deleteUserCompany(id);
    }

    @Override
    public List<UserCompanyDTO> getAllUserCompanyList() {
        UserCompanyDAO userCompanyDAO = dbService.getDao(UserCompanyDAO.class);

        return userCompanyDAO.getAllUserCompanyList();
    }

    @Override
    public List<UserCompanyDTO> getUserCompanyById(int id) {
        UserCompanyDAO userCompanyDAO = dbService.getDao(UserCompanyDAO.class);

        return userCompanyDAO.getUserCompanyById(id);
    }
}
