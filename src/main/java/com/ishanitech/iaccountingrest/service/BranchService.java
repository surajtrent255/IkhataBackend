package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BranchConfigDTO;
import com.ishanitech.iaccountingrest.dto.BranchDTO;
import com.ishanitech.iaccountingrest.dto.UserBranchDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigDTO;

import java.util.List;

public interface BranchService {

    List<BranchDTO> getBranchByCompanyId(int companyId);

    int addBranch(BranchDTO branchDTO);

    void AssignBranchToUser(UserBranchDTO userBranchDTO);

    List<UserBranchDTO> getBranchDetailsBasedOnCompanyAndUserId(int companyId,int userId);

    List<BranchConfigDTO> getBranchUserByCompanyId(int companyId);

    void enableDisableBranchUser(boolean status,int userId,int companyId,int branchId);

    List<UserConfigDTO> getUserForAssignBranchList(int companyId);

}
