package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.DesignationDTO;

import java.util.List;

public interface DesignationService {
    List<DesignationDTO> getAllDesignationForAStation(Integer companyId, Integer branchId);

    Integer addNewDesignation(DesignationDTO designation);
}
