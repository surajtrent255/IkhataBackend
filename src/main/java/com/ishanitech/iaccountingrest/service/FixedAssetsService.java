package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dao.FixedAssetsDAO;
import com.ishanitech.iaccountingrest.dto.FixedAssetsDTO;

import java.util.List;

public interface FixedAssetsService {

    Integer addFixedAssetsDetails(FixedAssetsDTO fixedAssetsDTO);

    List<FixedAssetsDTO> getFixedAssetsDetails(int companyId);

    FixedAssetsDTO getFixedAssetsBySN(int SN);

    void updateFixedAssets(FixedAssetsDTO fixedAssetsDTO);

    void deleteFromAssets(int SN);

}
