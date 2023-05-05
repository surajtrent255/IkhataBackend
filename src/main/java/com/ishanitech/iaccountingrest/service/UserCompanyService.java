package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.UserCompanyDTO;
import com.ishanitech.iaccountingrest.model.User;
import org.jdbi.v3.sqlobject.customizer.Bind;

import java.util.List;

public interface UserCompanyService {

    Integer addUserCompany(UserCompanyDTO userCompanyDTO);
    void deleteUserCompany(Integer idd);
    List<UserCompanyDTO> getAllUserCompanyList();

    List<UserCompanyDTO> getUserCompanyById(int id);

    List<CompanyDTO> getCompanyDetailsFromUserCompanyRoleTableForDisableCompany( int userId);


}
