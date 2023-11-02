package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.UserCommonConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface UserConfigutarionDAO {

@SqlQuery("select users.id as userId,users.firstname as firstname, users.lastname as lastname, users.email as email, company.name as companyName, "
        + "user_company_role.role_id as roleId, user_company_role.status as roleStatus, role.id as id, role.role as role "
        + "from users inner join user_company_role on users.id=user_company_role.user_id "
        + "inner join role on role.id=user_company_role.role_id "
        + "inner join company on company.company_id = user_company_role.company_id "
        + "where user_company_role.company_id=:companyId AND user_company_role.status = true")
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyId(@Bind("companyId") int companyId);

    @SqlQuery("""
            SELECT
                users.id AS userId,
                users.firstname AS firstname,
                users.lastname AS lastname,
                users.email AS email,
                user_company_role.role_id AS roleId,
                user_company_role.status AS roleStatus,
                role.id AS id,
                role.role AS role,
                company.name as companyName
            FROM
                users
            INNER JOIN
                user_company_role ON users.id = user_company_role.user_id
            INNER JOIN
                role ON role.id = user_company_role.role_id
            INNER JOIN company ON company.company_id = user_company_role.company_id 
            WHERE
                <caseQuery>
            """)
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO> getLimitedUserRoleForSearch(@Define String caseQuery);


    @SqlQuery("select users.id as userId,users.firstname as firstname, users.lastname as lastname, users.email as email, "
            + "user_company_role.role_id as roleId, user_company_role.status as roleStatus, role.id as id, role.role as role "
            + "from users inner join user_company_role on users.id=user_company_role.user_id "
            + "inner join role on role.id=user_company_role.role_id "
            + "where user_company_role.company_id=:companyId AND user_company_role.user_id=:userId AND user_company_role.status=true")
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyIdAndUserId(@Bind("companyId") int companyId,@Bind("userId") int userId);

    @SqlUpdate("UPDATE user_company_role SET  status = :status WHERE user_id = :userId AND company_id=:companyId AND role_id=:roleId ;")
    void updateUserRoleStatus(@Bind boolean status,@Bind int userId,@Bind int companyId,int roleId);

    @SqlUpdate("UPDATE user_company SET  status = :status WHERE user_id = :userId ;")
    void updateUserCompanyStatus(@Bind boolean status,@Bind int userId);

    @SqlUpdate("INSERT INTO user_company_role (user_id,company_id,role_id) VALUES (:userId,:companyId,:roleId)")
    int addRoleToUser(@Bind("userId") int userId,@Bind("companyId") int companyId,@Bind("roleId") int roleId);



    @SqlQuery(" SELECT DISTINCT users.id as userId, users.firstname as firstName, users.lastname as lastName,users.phone as phone, users.email as email " +
            " FROM users " +
            " LEFT JOIN user_company ON users.id = user_company.user_id " +
            "   INNER JOIN user_role on user_role.user_id =  users.id " +
            "   <caseQuery> ")
    @RegisterBeanMapper(UserCommonConfigDTO.class)
    List<UserCommonConfigDTO> getAllUser(@Define String caseQuery);

//    WHERE id NOT IN (SELECT user_id FROM user_company)
    @SqlQuery("""
            SELECT
                users.id AS userId,
                users.firstname AS firstName,
                users.lastname AS lastName,
                users.email AS email,
                user_company.status AS companyStatus,
                company.name AS companyName
            FROM
                users
            INNER JOIN
                user_company ON user_company.user_id = users.id
            INNER JOIN
                company ON user_company.company_id = company.company_id
            WHERE
                user_company.company_id = :companyId;         
            """)
    @RegisterBeanMapper(UserCommonConfigDTO.class)
    List<UserCommonConfigDTO> getUsersByCompanyId(@Bind("companyId") int companyId);

    @SqlQuery("""
            SELECT
                     users.id AS userId,
                     users.firstname AS firstName,
                     users.lastname AS lastName,
                     users.email AS email,
                     user_company.status AS companyStatus,
                     company.name AS companyName
                 FROM
                     users
                 INNER JOIN
                     user_company ON user_company.user_id = users.id
                 INNER JOIN
                     company ON user_company.company_id = company.company_id
                 WHERE
                <caseQuery>
            """)
    @RegisterBeanMapper(UserCommonConfigDTO.class)
    List<UserCommonConfigDTO> getLimitedUsersForSearchInUserConfiguration(@Define String caseQuery);

    @SqlUpdate("INSERT INTO user_company (company_id,user_id) VALUES (:companyId,:userId);")
    void AssignCompanyToUser(@Bind("companyId") int companyId,@Bind("userId") int userId);

//    For Super Admin

    @SqlUpdate(" UPDATE user_role " +
            " SET role_id=:roleId " +
            " WHERE user_id = :userId;")
    void assignAdminRoleToUserBySuperAdmin(@Bind int userId,@Bind int roleId);

}
