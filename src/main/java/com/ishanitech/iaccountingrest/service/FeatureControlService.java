package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.FeatureControlDTO;
import com.ishanitech.iaccountingrest.dto.UserFeatureDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;

import java.util.List;

public interface FeatureControlService {

    List<FeatureControlDTO> getFeatureControls();

    void assignFeatureControlToUser( int[] featureId, int userId, int companyId);

    List<UserFeatureDTO> getFeatureControlOfUsersForListing( int companyId);

    void enableDisableFeatureControlForUser( int userId, boolean status,int featureId);

    List<UserFeatureDTO> getAllFeatureControlOfUserByUserId(int companyId, int userId);

    List<UserFeatureDTO> getFeatureControlDetailsForLocalStorage( int companyId, int userId);



}
