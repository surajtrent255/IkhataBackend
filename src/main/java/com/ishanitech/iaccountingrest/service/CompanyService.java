package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyAndUserCompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyLogoDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CompanyService {


    int addCompany(CompanyDTO companyDTO,int userId);
    void deleteCompany(Integer companyId);
    List<CompanyDTO> getAllCompanyList();

    CompanyDTO getCompanyByPanNo(Long PanNo);

    List<CompanyAndUserCompanyDTO> getCompanyByUserId(int userId);

    List<CompanyDTO> getCustomerInfosByPanOrPhone(int searchMethod, long customerPhoneOrPan);

    void updateCompanyStatus( boolean status,  int companyId);

//    company Logo
//    public void addCompanyLogo(MultipartFile file,int companyId) throws IOException;

    public CompanyLogoDTO getCompanyLogo(int companyId);

    void addCompanyLogo(CompanyLogoDTO companyLogoDTO);

    CompanyAndUserCompanyDTO getCompanyByIdForEdit( int companyId);

    void editCompany( CompanyDTO companyDTO);

}
