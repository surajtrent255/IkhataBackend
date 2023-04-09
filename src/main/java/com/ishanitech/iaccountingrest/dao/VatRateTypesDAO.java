package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.VatRateTypesDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface VatRateTypesDAO {


    @SqlQuery("""
            SELECT vrt.id as id, vrt.vate_rate as vat_rate, vrt.vat_rate_num as vat_rate_num 
            	FROM public.vat_rate_type vrt;
            """)
    @RegisterBeanMapper(VatRateTypesDTO.class)
    List<VatRateTypesDTO> getAllVatRateTypes();
}
