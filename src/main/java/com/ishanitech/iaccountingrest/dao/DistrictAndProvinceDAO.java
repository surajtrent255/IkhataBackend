package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.DistrictDTO;
import com.ishanitech.iaccountingrest.dto.ProvinceDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface DistrictAndProvinceDAO {

    @SqlQuery("SELECT * FROM province")
    @RegisterBeanMapper(ProvinceDTO.class)
    List<ProvinceDTO>  getAllState();

    @SqlQuery("SELECT * FROM districts WHERE province_id=:provinceId")
    @RegisterBeanMapper(DistrictDTO.class)
    List<DistrictDTO> getDistrictByProvinceId(@Bind("provinceId") int provinceId);

}
