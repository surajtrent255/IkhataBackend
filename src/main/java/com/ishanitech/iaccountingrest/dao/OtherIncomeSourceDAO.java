package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface OtherIncomeSourceDAO {

    @SqlQuery("SELECT * FROM other_income_source WHERE company_id = :companyId AND branch_id = :branchId")
    @RegisterBeanMapper(OtherIncomeSourceDTO.class)
    List<OtherIncomeSourceDTO> getAllOtherIncomeSources(Integer companyId, Integer branchId);

    @SqlUpdate("INSERT INTO other_income_source (name, company_id, branch_id) VALUES (:name, :companyId, :branchId)")
    @GetGeneratedKeys
    int addNewOtherIncomeSource(@BindBean OtherIncomeSourceDTO otherIncomeSource);
}
