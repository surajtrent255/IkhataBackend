package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.DesignationDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface DesignationDAO {

    @SqlQuery("SELECT * FROM designation WHERE company_id = :companyId AND branch_id = :branchId")
    @RegisterBeanMapper(DesignationDTO.class)
    List<DesignationDTO> getALlDesignations(Integer companyId, Integer branchId);

    @SqlUpdate("INSERT INTO designation (title, company_id, branch_id) VALUES (:title, :companyId, :branchId)")
    @GetGeneratedKeys
    int addNewDesignation(@BindBean DesignationDTO designation);
}
