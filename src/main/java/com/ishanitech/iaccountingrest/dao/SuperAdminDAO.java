package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Define;
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

    @SqlQuery(" SELECT users.id AS userId, users.firstname AS firstname, users.lastname AS lastname, users.email AS email, " +
            " users.phone as phone,user_role.status as roleStatus,role.role as role,user_role.role_id as roleId" +
            " from users inner join user_role on users.id = user_role.user_id " +
            " inner join role on role.id = user_role.role_id WHERE <caseQuery> ; ")
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO>  fetchLimitedUsersForSuperAdminList(@Define String caseQuery);


    @SqlQuery("""
            SELECT * FROM company WHERE <caseQuery>
            """)
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> fetchCompanyWithNoUsers(@Define String caseQuery);

    @SqlQuery("""
            select * from users where deleted=false;
            """)
    @RegisterBeanMapper(UserDTO.class)
    List<UserDTO> getAllUsers();



    @SqlUpdate("""
            UPDATE company
            SET deleted = false, customer = false, is_approved = true
            WHERE company_id = :companyId;
                        
            UPDATE user_role
            SET role_id = 1, status = true
            WHERE user_id = :userId;
                        
            INSERT INTO user_company(company_id,user_id,status)
            VALUES(:companyId , :userId , true);
                        
            INSERT INTO user_company_role(user_id,role_id,company_id,status,deleted)
            VALUES(:userId , 1, :companyId , true , false);
            """)
    Integer assignUserWithNoCompany(int companyId, int userId);
}
