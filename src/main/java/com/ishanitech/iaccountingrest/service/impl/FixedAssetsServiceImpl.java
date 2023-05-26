package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.FixedAssetsDAO;
import com.ishanitech.iaccountingrest.dto.FixedAssetsDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.FixedAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedAssetsServiceImpl implements FixedAssetsService {

    @Autowired
    private DbService dbService;
    @Override
    public Integer addFixedAssetsDetails(FixedAssetsDTO fixedAssetsDTO) {
        FixedAssetsDAO fixedAssetsDAO = dbService.getDao(FixedAssetsDAO.class);
        int done = fixedAssetsDAO.addFixAssetsDetails(fixedAssetsDTO);
        return done;
    }

    @Override
    public List<FixedAssetsDTO> getFixedAssetsDetails(int companyId) {
        FixedAssetsDAO fixedAssetsDAO = dbService.getDao(FixedAssetsDAO.class);
        return fixedAssetsDAO.getFixedAssetsDetails(companyId);
    }

    @Override
    public FixedAssetsDTO getFixedAssetsBySN(int SN) {
        FixedAssetsDAO fixedAssetsDAO = dbService.getDao(FixedAssetsDAO.class);
        return fixedAssetsDAO.getFixedAssetsDetailsBySN(SN);
    }

    @Override
    public void updateFixedAssets(FixedAssetsDTO fixedAssetsDTO) {
        FixedAssetsDAO fixedAssetsDAO = dbService.getDao(FixedAssetsDAO.class);
            fixedAssetsDAO.updateFixedAssets(fixedAssetsDTO);
    }

    @Override
    public void deleteFromAssets(int SN) {
        FixedAssetsDAO fixedAssetsDAO = dbService.getDao(FixedAssetsDAO.class);
        fixedAssetsDAO.deleteFromFixedAssets(SN);
    }

    @Override
    public List<FixedAssetsDTO> getLimitedFixedAssetsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId) {
        List<FixedAssetsDTO> fixedAssets;
        fixedAssets = dbService.getDao(FixedAssetsDAO.class).getLimitedFixedAssetsByCompanyAndBranchId(offset, pageTotalItems, compId, branchId);
        return fixedAssets;
    }
}
