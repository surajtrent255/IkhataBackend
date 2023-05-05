package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CompanyDAO;
import com.ishanitech.iaccountingrest.dto.CompanyAndUserCompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.service.CompanyService;
import com.ishanitech.iaccountingrest.service.DbService;
import org.jdbi.v3.core.JdbiException;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public Integer addCompany(CompanyDTO companyDTO,int userId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        int savedCompanyId =0;
        try{
            savedCompanyId = companyDAO.addCompanyWithUserId(companyDTO,userId);
        }catch (JdbiException jdbiException){
            log.error("error creating Company");
        }

        return savedCompanyId;
//        return companyDAO.addCompany(companyDTO);
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
        return  companyDAO.getCompanyByPanNo(PanNo);
    }

    @Override
    public List<CompanyAndUserCompanyDTO> getCompanyByUserId(int userId) {
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
        return companyDAO.getCompanyByUserId(userId);
    }

    @Override
    public List<CompanyDTO> getCustomerInfosByPanOrPhone(int searchMethod, long customerPhoneOrPan) {
        List<CompanyDTO> customersInfos  = new ArrayList<>();
        CompanyDAO companyDAO = dbService.getDao(CompanyDAO.class);
//        CompanyDTO customer = null;
        customersInfos = companyDAO.getCompanyByPhoneNo(customerPhoneOrPan);
//
//        List<CompanyDTO> customerDTOS = new ArrayList<>();
//        switch (searchMethod) {
//            case 1 -> {
//                customer = companyDAO.getCompanyByPanNo(customerPhoneOrPan);
//                if(customer != null){
//                    customersInfos.add(customer);
//                }
//            }
//            case 2 -> {
//                customerDTOS = companyDAO.getCompanyByPhoneNo(customerPhoneOrPan);
//                customersInfos.addAll(customerDTOS);
//            }
//        }
        return customersInfos;
    }
}
