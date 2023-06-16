package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CompanyLabelDAO;
import com.ishanitech.iaccountingrest.dto.CompanyLabelInfoDTO;
import com.ishanitech.iaccountingrest.service.CompanyLabelService;
import com.ishanitech.iaccountingrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyLabelServiceImpl implements CompanyLabelService {
    @Autowired
    private DbService dbService;


    @Override
    public List<CompanyLabelInfoDTO> getCompanyLabelInfo(int companyId) {
        CompanyLabelDAO companyLabelDAO = dbService.getDao(CompanyLabelDAO.class);
        return companyLabelDAO.getCompanyLabelInfo(companyId);
    }

    @Override
    public CompanyLabelInfoDTO getCompanyLabelInfoByCompanyIdAndLabelName(int companyId, String labelName) {
        CompanyLabelDAO companyLabelDAO = dbService.getDao(CompanyLabelDAO.class);
        return companyLabelDAO.getCompanyLabelInfoByCompanyIdAndLabelName(companyId,labelName);
    }

    @Override
    public void addLabel(String name) {
        CompanyLabelDAO companyLabelDAO = dbService.getDao(CompanyLabelDAO.class);
        companyLabelDAO.addLabel(name);
    }

    @Override
    public void addLabelData(CompanyLabelInfoDTO companyLabelInfoDTO) {
        CompanyLabelDAO companyLabelDAO = dbService.getDao(CompanyLabelDAO.class);
        companyLabelDAO.addLabelData(companyLabelInfoDTO);
    }

    @Override
    public void deleteLabel(int id) {
        CompanyLabelDAO companyLabelDAO = dbService.getDao(CompanyLabelDAO.class);
        companyLabelDAO.deleteLabel(id);
    }

    @Override
    public List<CompanyLabelInfoDTO> getCompanyLabel() {
        CompanyLabelDAO companyLabelDAO = dbService.getDao(CompanyLabelDAO.class);
        return companyLabelDAO.getCompanyLabel();
    }
}
