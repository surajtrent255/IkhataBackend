package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.OtherIncomeDAO;
import com.ishanitech.iaccountingrest.dto.OtherIncomeDTO;
import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.EmailService;
import com.ishanitech.iaccountingrest.service.OtherIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OtherIncomeServiceImpl implements OtherIncomeService {
    private final DbService dbService;

    @Override
    public List<OtherIncomeDTO> getAllOtherIncomes(Integer companyId, Integer branchId) {
        OtherIncomeDAO otherIncomeDAO = dbService.getDao(OtherIncomeDAO.class);
        List<OtherIncomeDTO> otherIncomeDTOS;
        otherIncomeDTOS = otherIncomeDAO.getAllOtherIncome(companyId, branchId);
        return otherIncomeDTOS;
    }

    @Override
    public OtherIncomeDTO getOtherIncomeById(Integer id, Integer companyId, Integer branchId) {
        OtherIncomeDAO otherIncomeDAO = dbService.getDao(OtherIncomeDAO.class);
        OtherIncomeDTO otherIncome;
        otherIncome = otherIncomeDAO.getSingleOtherIncome(id, companyId, branchId);
        return otherIncome;
    }

    @Override
    public Integer insertNewOtherIncome(OtherIncomeDTO otherIncome) {
        OtherIncomeDAO otherIncomeDAO = dbService.getDao(OtherIncomeDAO.class);
        int id;
        id = otherIncomeDAO.addNewOtherIncome(otherIncome);
        return id;
    }

    @Override
    public void updateGivenOtherIncome(OtherIncomeDTO otherIncome) {
        OtherIncomeDAO otherIncomeDAO = dbService.getDao(OtherIncomeDAO.class);
        int id;
        id = otherIncomeDAO.updateParticularOtherIncome(otherIncome, otherIncome.getSn());
    }

    @Override
    public void deleteOtherIncomeById(Integer id, Integer companyId, Integer branchId) {
        OtherIncomeDAO otherIncomeDAO = dbService.getDao(OtherIncomeDAO.class);
        otherIncomeDAO.deleteParticularOtherIncome(id, companyId, branchId);
    }

    @Override
    public List<OtherIncomeSourceDTO> getAllOtherIncomeTypes() {
        OtherIncomeDAO otherIncomeDAO = dbService.getDao(OtherIncomeDAO.class);
        List<OtherIncomeSourceDTO> otherIncomeTypeDTOS;
        otherIncomeTypeDTOS = otherIncomeDAO.getAllOtherIncomeTypes();
        return otherIncomeTypeDTOS;
    }
}
