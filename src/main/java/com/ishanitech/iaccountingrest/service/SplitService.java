package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.SplitProductDTO;

import java.util.List;

public interface SplitService {

    List <SplitProductDTO> getAllSplitProduct(int companyId, int branchId);

    int addsplit(SplitProductDTO splitProductDTO);

    int updateSplit(SplitProductDTO splitProductDTO);

    List <SplitProductDTO> getAllSplitProductByProductId(int productId);

    List<SplitProductDTO> getSplitProductById(int id);

    void updateSplitAgain(SplitProductDTO splitProductDTO);

    void updateMerge(SplitProductDTO splitProductDTO);

    List<SplitProductDTO> getLimitedSplitProductByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
