package com.ishanitech.iaccountingrest.converter.impl;

import com.ishanitech.iaccountingrest.converter.BaseConverter;
import com.ishanitech.iaccountingrest.dto.LgProvinceDTO;
import com.ishanitech.iaccountingrest.model.LgProvince;

import java.util.List;
import java.util.stream.Collectors;

public class LgProvinceConverter extends BaseConverter<LgProvince, LgProvinceDTO> {


    @Override
    public LgProvince fromDto(LgProvinceDTO dto) {
        LgProvince province = new LgProvince();
        province.setProvinceId(dto.getProvinceId());
        province.setProvinceName(dto.getProvinceName());
        province.setProvinceNameNep(dto.getProvinceNameNep());

        return province;
    }

    @Override
    public LgProvinceDTO fromEntity(LgProvince entity) {
        LgProvinceDTO provinceDTO = new LgProvinceDTO();
        provinceDTO.setProvinceId(entity.getProvinceId());
        provinceDTO.setProvinceName(entity.getProvinceName());
        provinceDTO.setProvinceNameNep(entity.getProvinceNameNep());

        return provinceDTO;
    }

    @Override
    public List<LgProvince> fromDto(List<LgProvinceDTO> dtos) {
        return dtos.stream().map(this::fromDto).collect(Collectors.toList());
    }


    @Override
    public List<LgProvinceDTO> fromEntity(List<LgProvince> entities) {
        return entities.stream().map(this::fromEntity).collect(Collectors.toList());
    }

}