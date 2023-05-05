package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.UnitDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface UnitDAO {
//    @SqlQuery("SELECT * FROM unit")
    @SqlQuery("""
            SELECT unit.id as Id, unit.name as Name FROM public.unit unit;
            """)
    @RegisterBeanMapper(UnitDTO.class)
    List<UnitDTO> getAllUnit();
}
