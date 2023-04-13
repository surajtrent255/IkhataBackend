package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.DistrictDTO;
import com.ishanitech.iaccountingrest.dto.ProvinceDTO;

import java.util.List;

public interface DistrictAndProvinceService {

    List<ProvinceDTO> getAllState();

    List<DistrictDTO> getDistrictByProvinceId(int provinceId);

}
