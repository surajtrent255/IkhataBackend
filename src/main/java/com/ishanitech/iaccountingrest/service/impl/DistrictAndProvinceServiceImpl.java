package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.DistrictAndProvinceDAO;
import com.ishanitech.iaccountingrest.dto.DistrictDTO;
import com.ishanitech.iaccountingrest.dto.ProvinceDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.DistrictAndProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictAndProvinceServiceImpl implements DistrictAndProvinceService {

    private final DbService dbService;

    public DistrictAndProvinceServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }

    @Override
    public List<ProvinceDTO> getAllState() {
        DistrictAndProvinceDAO districtAndProvinceDAO = dbService.getDao(DistrictAndProvinceDAO.class);
        return districtAndProvinceDAO.getAllState();
    }

    @Override
    public List<DistrictDTO> getDistrictByProvinceId(int provinceId) {
        DistrictAndProvinceDAO districtAndProvinceDAO = dbService.getDao(DistrictAndProvinceDAO.class);
        return districtAndProvinceDAO.getDistrictByProvinceId(provinceId);
    }
}
