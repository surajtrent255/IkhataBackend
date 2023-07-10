package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.FeatureControlDAO;
import com.ishanitech.iaccountingrest.dto.FeatureControlDTO;
import com.ishanitech.iaccountingrest.dto.UserFeatureDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.FeatureControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureControlServiceImpl implements FeatureControlService {
    @Autowired
    private DbService dbService;
    @Override
    public List<FeatureControlDTO> getFeatureControls() {
        FeatureControlDAO featureControlDAO = dbService.getDao(FeatureControlDAO.class);
        return featureControlDAO.getFeatureControls();
    }

    @Override
    public void assignFeatureControlToUser(int[] featureId, int userId, int companyId) {
        FeatureControlDAO featureControlDAO = dbService.getDao(FeatureControlDAO.class);
        for(int feature : featureId){
            featureControlDAO.assignFeatureControlToUser(feature,userId,companyId);
        }
    }

    @Override
    public List<UserFeatureDTO> getFeatureControlOfUsersForListing(int companyId) {
        FeatureControlDAO featureControlDAO = dbService.getDao(FeatureControlDAO.class);
        return featureControlDAO.getFeatureControlOfUsersForListing(companyId);
    }

    @Override
    public void enableDisableFeatureControlForUser(int userId, boolean status,int featureId) {
        FeatureControlDAO featureControlDAO = dbService.getDao(FeatureControlDAO.class);
        featureControlDAO.enableDisableFeatureControlForUser(userId,status,featureId);
    }

    @Override
    public List<UserFeatureDTO> getAllFeatureControlOfUserByUserId(int companyId, int userId) {
        FeatureControlDAO featureControlDAO = dbService.getDao(FeatureControlDAO.class);
        return featureControlDAO.getAllFeatureControlOfUserByUserId(companyId,userId);
    }

    @Override
    public List<UserFeatureDTO> getFeatureControlDetailsForLocalStorage(int companyId, int userId) {
        FeatureControlDAO featureControlDAO = dbService.getDao(FeatureControlDAO.class);
        return featureControlDAO.getFeatureControlDetailsForLocalStorage(companyId,userId);
    }

    @Override
    public List<UserFeatureDTO> getLimitedUserFeatureForSearch(Integer offset, Integer pageTotalItems, Integer companyId, String searchInput) {
        String caseQuery = "";
        if (searchInput.length() != 0) {
            caseQuery = " uf.company_id = " + companyId + " AND u.email = '" + searchInput + "' order by u.phone desc" + " limit " + pageTotalItems + " offset " + (offset - 1);
        } else {
            caseQuery = " uf.company_id = " + companyId + " order by u.phone desc" + " limit " + pageTotalItems + " offset " + (offset - 1);
        }
        FeatureControlDAO featureControlDAO = dbService.getDao(FeatureControlDAO.class);
        return featureControlDAO.getLimitedUserFeatureControlForSearch(caseQuery);
    }


}
