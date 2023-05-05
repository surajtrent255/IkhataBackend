package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.UnitDAO;
import com.ishanitech.iaccountingrest.dto.UnitDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.UnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UnitServiceImpl implements UnitService{
    private final DbService dbService;

    public UnitServiceImpl(DbService dbService) {
        this.dbService = dbService;
    }


    @Override
    public List<UnitDTO> getAllUnit() {
        UnitDAO unitDAO = dbService.getDao(UnitDAO.class);
        List<UnitDTO> UnitDTOS = unitDAO.getAllUnit();
        System.out.println(UnitDTOS);
        return UnitDTOS;
    }
}
