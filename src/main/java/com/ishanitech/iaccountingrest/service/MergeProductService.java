package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.MergeProductDTO;

import java.util.List;

public interface MergeProductService {
    List<MergeProductDTO> getAllMergeProduct(int companyId, int branchId);

    int addMerge(MergeProductDTO mergeProductDTO);

    int updateMerge(MergeProductDTO mergeProductDTO);
}