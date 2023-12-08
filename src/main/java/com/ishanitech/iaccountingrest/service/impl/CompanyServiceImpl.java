package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BillNoGeneratorDAO;
import com.ishanitech.iaccountingrest.dao.BranchDAO;
import com.ishanitech.iaccountingrest.dao.CompanyDAO;
import com.ishanitech.iaccountingrest.dto.*;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CompanyService;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.utils.CustomQueryCreator;
import com.ishanitech.iaccountingrest.utils.HostDetailsUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.jdbi.v3.core.JdbiException;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private final DbService dbService;

    public CompanyServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Autowired
    private HostDetailsUtil resolveHostAddress;

    @Override
    public int addCompany(CompanyDTO companyDTO, int userId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        int savedCompanyId = 0;
        try {
            savedCompanyId = companyDAO.addCompanyWithUserId(companyDTO, userId);
            // BranchDTO branchDTO = new BranchDTO();
            // branchDTO.setCompanyId(savedCompanyId);
            // int branchAdded = branchDAO.addBranch(branchDTO);
            if (!companyDTO.isCustomer()) {
                BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
                String currentFiscalYear = billNoGeneratorDAO.getCurrentFiscalYear();
                List<BillNoGenerationDTO> billNoGenerationDTOS = new ArrayList<>();
                BillNoGenerationDTO billNoGeneration = new BillNoGenerationDTO();
                billNoGeneration.setFiscalYear(currentFiscalYear);
                billNoGeneration.setCompanyId(savedCompanyId);
                billNoGeneration.setBranchId(0);
                billNoGeneration.setHasAbbr(false);
                billNoGeneration.setActive(true);
                billNoGenerationDTOS.add(billNoGeneration);

                BillNoGenerationDTO billNoGeneration2 = new BillNoGenerationDTO();
                billNoGeneration2.setFiscalYear(currentFiscalYear);
                billNoGeneration2.setCompanyId(savedCompanyId);
                billNoGeneration2.setBranchId(0);
                billNoGeneration2.setHasAbbr(true);
                billNoGeneration2.setActive(true);
                billNoGenerationDTOS.add(billNoGeneration2);
                billNoGeneratorDAO.createNewFiscalYear(billNoGenerationDTOS);

                // for receipt no generation
                billNoGeneratorDAO.createNewRecieptNo(currentFiscalYear, savedCompanyId, 0);
            }

        } catch (JdbiException jdbiException) {
            log.error("error creating Company");
            throw new CustomSqlException(jdbiException.getMessage());
        }

        return savedCompanyId;
        // return companyDAO.addCompany(companyDTO);
    }

    @Override
    public void deleteCompany(Integer companyId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        companyDAO.deleteCompany(companyId);
    }

    @Override
    public List<CompanyDTO> getAllCompanyList() {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        List<CompanyDTO> companyDTOS = companyDAO.getAllCompanyList();
        return companyDTOS;
    }

    @Override
    public CompanyDTO getCompanyByPanNo(Long PanNo) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        return companyDAO.getCompanyByPanNo(PanNo);
    }

    @Override
    public List<CompanyDTO> getCompanyByUserId(int userId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        List<CompanyDTO> companyDTOS = companyDAO.getCompanyByUserId(userId);

        for (CompanyDTO companyDTO : companyDTOS) {
            if(companyDTO.getImageName() != null){
                String imageUrl = resolveHostAddress.getHostUrl() + "images/" + companyDTO.getImageName();
                //            for Production comment above line and uncomment below line
//            String imageUrl = "http://103.233.58.121:9999/"+"images/"+companyDTO.getImageName();
                companyDTO.setImageUrl(imageUrl);

            } else {
                companyDTO.setImageUrl(null);
            }
        }

        return companyDTOS;
    }

    @Override
    public List<CompanyDTO> getCustomerInfosByPanOrPhone(int searchMethod, long customerPhoneOrPan) {
        List<CompanyDTO> customersInfos = new ArrayList<>();
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        // CompanyDTO customer = null;
        customersInfos = companyDAO.getCompanyByPhoneNo(customerPhoneOrPan);
        //
        // List<CompanyDTO> customerDTOS = new ArrayList<>();
        // switch (searchMethod) {
        // case 1 -> {
        // customer = companyDAO.getCompanyByPanNo(customerPhoneOrPan);
        // if(customer != null){
        // customersInfos.add(customer);
        // }
        // }
        // case 2 -> {
        // customerDTOS = companyDAO.getCompanyByPhoneNo(customerPhoneOrPan);
        // customersInfos.addAll(customerDTOS);
        // }
        // }
        return customersInfos;
    }

    @Override
    public void updateCompanyStatus(boolean status, int companyId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        companyDAO.updateCompanyStatus(status, companyId);
    }

    @Override
    public CompanyLogoDTO getCompanyLogo(int companyId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        return companyDAO.getCompanyLogo(companyId);
    }

    @Override
    public void addCompanyLogo(CompanyLogoDTO companyLogoDTO) {
        try {
            CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
            companyDAO.addCompanyLogo(companyLogoDTO);
        } catch (JdbiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public CompanyDTO getCompanyByIdForEdit(int companyId) {
        try {
            CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);

            CompanyDTO companyDTO = companyDAO.getCompanyByIdForEdit(companyId);

            String imageUrl = resolveHostAddress.getHostUrl() + "images/" + companyDTO.getImageName();
//                        for Production comment above line and uncomment below line
//            String imageUrl = "http://103.233.58.121:9999/"+"images/"+companyDTO.getImageName();

            companyDTO.setImageUrl(imageUrl);
            return companyDTO;

        } catch (JdbiException e) {
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @Override
    public void editCompany(CompanyDTO companyDTO) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        try {
            companyDAO.editCompany(companyDTO);
        } catch (JdbiException e) {
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @Override
    public void editCompanyLogo(String imageName, int companyId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        companyDAO.editCompanyLogo(imageName, companyId);
    }

    @Override
    public Integer customerAddedToday(String todayDate, int companyId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        return companyDAO.customerAddedToday(todayDate, companyId);
    }

    @Override
    public Integer customerAddedInMonth(String month, int companyId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        String caseQuery = "company_id=" + companyId + " AND customer='true' AND created_date_nepali LIKE '%-" + month
                + "-%'";
        return companyDAO.customerAddedInMonth(caseQuery);
    }

    @Override
    public Integer customerAddedThisYear(String fiscalYear, int companyId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        String caseQuery = "company_id=" + companyId + " AND customer='true' AND created_date_nepali LIKE '%"
                + fiscalYear + "%'";
        return companyDAO.customerAddedThisYear(caseQuery);
    }

    @Override
    public List<CompanyDTO> getAllCustomersByCompAndBranchId(HttpServletRequest request) {
        String caseQuery = CustomQueryCreator.generateQueryWithCase(request, PaginationTypeClass.CUSTOMER, dbService);
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        List<CompanyDTO> customers = companyDAO.getAllCustomers(caseQuery);
        return customers;
    }

    @Override
    public CompanyDTO getSingleCompanyById(Integer id) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        CompanyDTO c1 = companyDAO.getSingleCompany(id);
        return c1;
    }
}
