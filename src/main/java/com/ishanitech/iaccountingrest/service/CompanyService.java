package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyLogoDTO;

import java.util.List;

public interface CompanyService {


    int addCompany(CompanyDTO companyDTO,int userId);
    void deleteCompany(Integer companyId);
    List<CompanyDTO> getAllCompanyList();

    CompanyDTO getCompanyByPanNo(Long PanNo);

    List<CompanyDTO> getCompanyByUserId(int userId);

    List<CompanyDTO> getCustomerInfosByPanOrPhone(int searchMethod, long customerPhoneOrPan);

    void updateCompanyStatus( boolean status,  int companyId);

//    company Logo
//    public void addCompanyLogo(MultipartFile file,int companyId) throws IOException;

    public CompanyLogoDTO getCompanyLogo(int companyId);

    void addCompanyLogo(CompanyLogoDTO companyLogoDTO);

    CompanyDTO getCompanyByIdForEdit(int companyId);

    void editCompany( CompanyDTO companyDTO);

    void editCompanyLogo( String imageName, int companyId);


}
