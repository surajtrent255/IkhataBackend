package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface SuperAdminDAO {

    @SqlQuery("""
            SELECT
                c.company_id AS companyId,
                c.name AS name,
                c.description AS description,
                c.pan_no AS panNo,
                c.state AS state,
                c.district AS district,
                c.mun_vdc AS munVdc,
                c.ward_no AS wardNo,
                c.email AS email,
                c.phone AS phone,
                c.owner_name AS ownerName,
                c.landline_number AS landlineNumber,
                c.registration_type AS registrationType,
                c.created_date AS createdDate,
                c.created_date_nepali AS createdDateNepali,
                c.is_approved AS isApproved
            FROM
                company c
                inner join user_company_role ucr on c.company_id = ucr.company_id
                WHERE ucr.role_id=1 AND ucr.user_id=:userId
            """)
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getCompanyDetailsOfUserForSuperAdmin(int userId);


    @SqlUpdate("""
            UPDATE company
            	SET   is_approved=:status
            	WHERE company_id = :companyId;
            """)
    void allowDisallowUserToProceedBySuperAdmin(int companyId,Boolean status);
}
