package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.DistrictDTO;
import com.ishanitech.iaccountingrest.dto.MunicipalityDTO;
import com.ishanitech.iaccountingrest.dto.ProvinceDTO;

import java.util.List;

public interface DistrictMunicipalityAndProvinceService {

    List<ProvinceDTO> getAllState();

    List<DistrictDTO> getDistrictByProvinceId(int provinceId);

    List<MunicipalityDTO> getMunicipalityByProvinceAndDistrictId(int provinceId,int districtId);

}
