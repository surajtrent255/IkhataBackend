package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.DesignationDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface DesignationDAO {

    @SqlQuery("SELECT * FROM designation WHERE companyId = :companyId AND branchId = :branchId")
    @RegisterBeanMapper(DesignationDTO.class)
    List<DesignationDTO> getALlDesignations(Integer companyId, Integer branchId);

    @SqlUpdate("INSERT INTO designation (title, companyId, branchId) VALUES (:title, :companyId, :branchId)")
    @GetGeneratedKeys
    int insertDesignation(@BindBean DesignationDTO designation);
    int addNewDesignation(DesignationDTO designation);
}
