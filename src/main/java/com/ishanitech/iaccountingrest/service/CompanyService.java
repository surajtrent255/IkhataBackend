package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyAndUserCompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {


    Integer addCompany(CompanyDTO companyDTO,int userId);
    void deleteCompany(Integer companyId);
    List<CompanyDTO> getAllCompanyList();

    CompanyDTO getCompanyByPanNo(Long PanNo);

    List<CompanyAndUserCompanyDTO> getCompanyByUserId(int userId);

    List<CompanyDTO> getCustomerInfosByPanOrPhone(int searchMethod, long customerPhoneOrPan);
}
