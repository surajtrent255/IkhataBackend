package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.OtherIncomeSourceDAO;
import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.OtherIncomeSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OtherIncomeSourceServiceImpl implements OtherIncomeSourceService {
    private final DbService dbService;

    @Override
    public List<OtherIncomeSourceDTO> getAllOtherIncomeSources(Integer companyId, Integer branchId) {
        OtherIncomeSourceDAO otherIncomeSourceDAO = dbService.getDao(OtherIncomeSourceDAO.class);
        List<OtherIncomeSourceDTO> otherIncomeSourceDTOS;
        otherIncomeSourceDTOS = otherIncomeSourceDAO.getAllOtherIncomeSources(companyId, branchId);
        return otherIncomeSourceDTOS;
    }

    @Override
    public Integer addNewOtherIncomeSource(OtherIncomeSourceDTO otherIncomeSource) {
        OtherIncomeSourceDAO otherIncomeSourceDAO = dbService.getDao(OtherIncomeSourceDAO.class);
        return otherIncomeSourceDAO.addNewOtherIncomeSource(otherIncomeSource);
    }
}
