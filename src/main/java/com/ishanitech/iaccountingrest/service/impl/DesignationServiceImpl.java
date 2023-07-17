package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.DesignationDAO;
import com.ishanitech.iaccountingrest.dto.DesignationDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.DesignationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesignationServiceImpl implements DesignationService {
    private final DbService dbService;
    @Override
    public List<DesignationDTO> getAllDesignationForAStation(Integer companyId, Integer branchId) {
        DesignationDAO designationDAO = dbService.getDao(DesignationDAO.class);
        List<DesignationDTO> designations;
        designations = designationDAO.getALlDesignations(companyId, branchId);
        return designations;
    }

    @Override
    public Integer addNewDesignation(DesignationDTO designation) {
        DesignationDAO designationDAO = dbService.getDao(DesignationDAO.class);
        int designationId=0;
         designationId = designationDAO.addNewDesignation(designation);
        return designationId;
    }
}
