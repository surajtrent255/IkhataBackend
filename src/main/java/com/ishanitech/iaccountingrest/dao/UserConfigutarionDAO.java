package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserRoleConfigDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface UserConfigutarionDAO {

    @SqlQuery("select users.firstname as firstname"
            + ",users.lastname as lastname"
            + ",users.email as email"
            + ",user_company.company_id as companyId"
            + ",user_company.user_id as userId"
            + ",user_company.status as companyStatus"
            + ",company.name as companyName"
            + ",user_role.role_id as roleId,"
            + "user_role.status as roleStatus"
            + ",role.id as id"
            + ",role.role as role"
            + " from users inner join user_company on "
            + " users.id = user_company.user_id inner join company"
            + " on user_company.company_id = company.company_id"
            + " inner join user_role on users.id=user_role.user_id inner join"
            + " role on role.id=user_role.role_id where user_company.company_id=:companyId")
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO> getUserConfigurationDetails(@Bind("companyId") int companyId);

    @SqlUpdate("UPDATE user_role SET  status = :status WHERE user_id = :userId ;")
    void updateUserStatus(@Bind boolean status,@Bind int userId);

    @SqlUpdate("UPDATE user_company SET  status = :status WHERE user_id = :userId ;")
    void updateUserCompanyStatus(@Bind boolean status,@Bind int userId);

    @SqlUpdate("INSERT INTO user_role (user_id,role_id) VALUES (:userId,:roleId)")
    @RegisterBeanMapper(UserRoleConfigDTO.class)
    int addRoleToUser(@Bind("userId") int userId,@Bind("roleId") int roleId);

    @SqlUpdate("UPDATE user_role SET  company_id = :companyId WHERE user_id = :userId ;")
    void updateUserRoleCompany(@Bind("companyId") int companyId,@Bind("userId") int userId);

    @SqlUpdate("UPDATE user_role SET role_id=1 WHERE user_id = :userId ;")
    void updateUserRole(@Bind("userId") int userId);

    @SqlQuery("SELECT id as userId ,firstname as firstName,lastname as lastName,email as email FROM users")
    @RegisterBeanMapper(UserConfigDTO.class)
    List<UserConfigDTO> getAllUser();

    @SqlQuery("SELECT users.id as userId ,users.firstname as firstName,users.lastname as lastName,users.email as email,user_company.status as companyStatus FROM users inner join user_company on user_company.user_id = users.id WHERE user_company.company_id = :companyId ;")
    @RegisterBeanMapper(UserConfigDTO.class)
    List<UserConfigDTO> getUsersByCompanyId(@Bind("companyId") int companyId);

    @SqlUpdate("INSERT INTO user_company (company_id,user_id) VALUES (:companyId,:userId);")
    void AssignCompanyToUser(@Bind("companyId") int companyId,@Bind("userId") int userId);

}
