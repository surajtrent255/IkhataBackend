package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BillNoGeneratorDAO;
import com.ishanitech.iaccountingrest.dao.BranchDAO;
import com.ishanitech.iaccountingrest.dto.BranchConfigDTO;
import com.ishanitech.iaccountingrest.dto.BillNoGenerationDTO;
import com.ishanitech.iaccountingrest.dto.BranchDTO;
import com.ishanitech.iaccountingrest.dto.UserBranchDTO;
import com.ishanitech.iaccountingrest.dto.UserCommonConfigDTO;
import com.ishanitech.iaccountingrest.service.BranchService;
import com.ishanitech.iaccountingrest.service.DbService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final DbService dbService;

    public BranchServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }



    @Override
    public List<BranchDTO> getBranchByCompanyId(int companyId) {
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        return branchDAO.getBranchByCompanyId(companyId);
    }

    @Override
    public int addBranch(BranchDTO branchDTO) {
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
      int branchAdded = branchDAO.addBranch(branchDTO);
        BillNoGeneratorDAO billNoGeneratorDAO = dbService.getDao(BillNoGeneratorDAO.class);
        String currentFiscalYear = billNoGeneratorDAO.getCurrentFiscalYear();
        List<BillNoGenerationDTO> billNoGenerations = getBillNoGenerationDTOS(branchDTO, currentFiscalYear, branchAdded);
        billNoGeneratorDAO.createNewFiscalYear(billNoGenerations);

        billNoGeneratorDAO.createNewRecieptNo(currentFiscalYear, branchDTO.getCompanyId(), branchAdded);
      return branchAdded;

    }

    private static List<BillNoGenerationDTO> getBillNoGenerationDTOS(BranchDTO branchDTO, String currentFiscalYear, int branchAdded) {
        BillNoGenerationDTO billNoGeneration = new BillNoGenerationDTO();
        billNoGeneration.setFiscalYear(currentFiscalYear);
        billNoGeneration.setCompanyId(branchDTO.getCompanyId());
        billNoGeneration.setBranchId(branchAdded);
        billNoGeneration.setHasAbbr(false);
        billNoGeneration.setActive(true);
        List<BillNoGenerationDTO> billNoGenerations =new ArrayList<>();
        billNoGenerations.add(billNoGeneration);

        BillNoGenerationDTO billNoGeneration2 = new BillNoGenerationDTO();
        billNoGeneration2.setFiscalYear(currentFiscalYear);
        billNoGeneration2.setCompanyId(branchDTO.getCompanyId());
        billNoGeneration2.setBranchId(branchAdded);
        billNoGeneration2.setHasAbbr(true);
        billNoGeneration2.setActive(true);
        billNoGenerations.add(billNoGeneration2);
        return billNoGenerations;
    }

    @Override
    public void AssignBranchToUser(UserBranchDTO userBranchDTO) {
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        branchDAO.AssignBranchToUser(userBranchDTO);
    }

    @Override
    public List<UserBranchDTO> getBranchDetailsBasedOnCompanyAndUserId(int companyId, int userId) {
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        return branchDAO.getBranchDetailsByCompanyAndUserId(companyId,userId);
    }

    @Override
    public List<BranchConfigDTO> getBranchUserByCompanyId(int companyId) {
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        return branchDAO.getBranchUsersByCompanyId(companyId);
    }

    @Override
    public void enableDisableBranchUser(boolean status, int userId, int companyId, int branchId) {
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        branchDAO.enableDisableBranchUser(status,userId,companyId,branchId);
    }

    @Override
    public List<UserCommonConfigDTO> getUserForAssignBranchList(int companyId) {
        BranchDAO branchDAO = dbService.getDao(BranchDAO.class);
        return branchDAO.getUserForAssignBranchList(companyId);
    }
}
