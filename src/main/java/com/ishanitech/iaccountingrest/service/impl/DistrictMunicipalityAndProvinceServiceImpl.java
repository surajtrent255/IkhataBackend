package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.DistrictMunicipalityAndProvinceDAO;
import com.ishanitech.iaccountingrest.dto.DistrictDTO;
import com.ishanitech.iaccountingrest.dto.MunicipalityDTO;
import com.ishanitech.iaccountingrest.dto.ProvinceDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.DistrictMunicipalityAndProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictMunicipalityAndProvinceServiceImpl implements DistrictMunicipalityAndProvinceService {

    private final DbService dbService;

    public DistrictMunicipalityAndProvinceServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<ProvinceDTO> getAllState() {
        DistrictMunicipalityAndProvinceDAO districtAndProvinceDAO = dbService.getDao(DistrictMunicipalityAndProvinceDAO.class);
        return districtAndProvinceDAO.getAllState();
    }

    @Override
    public List<DistrictDTO> getDistrictByProvinceId(int provinceId) {
        DistrictMunicipalityAndProvinceDAO districtAndProvinceDAO = dbService.getDao(DistrictMunicipalityAndProvinceDAO.class);
        return districtAndProvinceDAO.getDistrictByProvinceId(provinceId);
    }

    @Override
    public List<MunicipalityDTO> getMunicipalityByProvinceAndDistrictId(int provinceId, int districtId) {
     DistrictMunicipalityAndProvinceDAO districtMunicipalityAndProvinceDAO = dbService.getDao(DistrictMunicipalityAndProvinceDAO.class);
     return districtMunicipalityAndProvinceDAO.getMunicipalityByProvinceAndDistrictId(provinceId,districtId);
    }
}
