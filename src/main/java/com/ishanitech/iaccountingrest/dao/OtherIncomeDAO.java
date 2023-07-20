
package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.OtherIncomeDTO;
import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface OtherIncomeDAO {
    @SqlQuery("""
        select * from other_income where company_id = :companyId and branch_id = :branchId and deleted = false order by sn desc;
        """)
    @RegisterBeanMapper(OtherIncomeDTO.class)
    List<OtherIncomeDTO> getAllOtherIncome(int companyId, int branchId);

    @SqlQuery("""
        select * from other_income where company_id = :companyId and branch_id = :branchId and sn = :id and deleted = false;
        """)
    @RegisterBeanMapper(OtherIncomeDTO.class)
    OtherIncomeDTO getSingleOtherIncome(int id, int companyId, int branchId);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO other_income (source, amount, date_english, date_nepali, company_id, branch_id)"
            + " VALUES (:source, :amount, :dateEnglish, :dateNepali, :companyId, :branchId)")
    int addNewOtherIncome(@BindBean OtherIncomeDTO otherIncome);

    @SqlUpdate("UPDATE other_income SET source = :source, amount = :amount, date_english = :dateEnglish, date_nepali = :dateNepali, "
            + "company_id = :companyId, branch_id = :branchId WHERE sn = :sn")
    int updateParticularOtherIncome(@BindBean OtherIncomeDTO otherIncome, int sn);

    @SqlUpdate("UPDATE other_income SET deleted = true WHERE sn = :id")
    int deleteParticularOtherIncome(int id, int companyId, int branchId);

    @SqlQuery("select * from other_income_source")
    @RegisterBeanMapper(OtherIncomeSourceDTO.class)
    List<OtherIncomeSourceDTO> getAllOtherIncomeTypes();
}