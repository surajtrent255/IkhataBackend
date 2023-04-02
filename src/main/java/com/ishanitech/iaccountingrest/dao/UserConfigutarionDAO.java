package com.ishanitech.iaccountingrest.dao;

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
            + ",company.name as companyName"
            + ",user_role.role_id as roleId,"
            + "user_role.status as status"
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

    @SqlUpdate("UPDATE user_company SET  status = :status WHERE company_id = :companyId ;")
    void updateUserCompanyStatus(@Bind boolean status,@Bind int companyId);

    @SqlUpdate("INSERT INTO user_role (user_id,role_id) VALUES (:userId,:roleId)")
    @RegisterBeanMapper(UserRoleConfigDTO.class)
    int addRoleToUser(@Bind("userId") int userId,@Bind("roleId") int roleId);

    @SqlUpdate("UPDATE user_role SET  company_id = :companyId WHERE user_id = :userId ;")
    void updateUserRoleCompany(@Bind("companyId") int companyId,@Bind("userId") int userId);

}
