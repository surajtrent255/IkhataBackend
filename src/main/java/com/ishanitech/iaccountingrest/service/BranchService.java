package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BranchDTO;
import com.ishanitech.iaccountingrest.dto.UserBranchDTO;

import java.util.List;

public interface BranchService {

    List<BranchDTO> getBranchByCompanyId(int companyId);

    int addBranch(BranchDTO branchDTO);

    void AssignBranchToUser(UserBranchDTO userBranchDTO);

    List<UserBranchDTO> getBranchDetailsBasedOnCompanyAndUserId(int companyId,int userId);

}
