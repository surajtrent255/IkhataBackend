package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.UserConfigDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface UserConfigutarionDAO {

@SqlQuery("select users.id as userId,users.firstname as firstname, users.lastname as lastname, users.email as email, "
        + "user_role.role_id as roleId, user_role.status as roleStatus, role.id as id, role.role as role "
        + "from users inner join user_role on users.id=user_role.user_id "
        + "inner join role on role.id=user_role.role_id "
        + "where user_role.company_id=:companyId")
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyId(@Bind("companyId") int companyId);

    @SqlQuery("select users.id as userId,users.firstname as firstname, users.lastname as lastname, users.email as email, "
            + "user_role.role_id as roleId, user_role.status as roleStatus, role.id as id, role.role as role "
            + "from users inner join user_role on users.id=user_role.user_id "
            + "inner join role on role.id=user_role.role_id "
            + "where user_role.company_id=:companyId AND user_role.user_id=:userId")
    @RegisterBeanMapper(UserConfigurationDTO.class)
    List<UserConfigurationDTO> getUserRoleDetailsBasedOnCompanyIdAndUserId(@Bind("companyId") int companyId,@Bind("userId") int userId);

    @SqlUpdate("UPDATE user_role SET  status = :status WHERE user_id = :userId AND company_id=:companyId AND role_id=:roleId ;")
    void updateUserRoleStatus(@Bind boolean status,@Bind int userId,@Bind int companyId,int roleId);

    @SqlUpdate("UPDATE user_company SET  status = :status WHERE user_id = :userId ;")
    void updateUserCompanyStatus(@Bind boolean status,@Bind int userId);

    @SqlUpdate("INSERT INTO user_role (user_id,company_id,role_id) VALUES (:userId,:companyId,:roleId)")
    int addRoleToUser(@Bind("userId") int userId,@Bind("companyId") int companyId,@Bind("roleId") int roleId);


//    Naya Banauna parxa
    @SqlQuery("SELECT DISTINCT users.id as userId, users.firstname as firstName, users.lastname as lastName, users.email as email " +
            " FROM users " +
            " LEFT JOIN user_company ON users.id = user_company.user_id " +
            " WHERE user_company.company_id <> :companyId OR user_company.company_id IS NULL; ")
    @RegisterBeanMapper(UserConfigDTO.class)
    List<UserConfigDTO> getAllUser(@Bind int companyId);

//    WHERE id NOT IN (SELECT user_id FROM user_company)
    @SqlQuery("SELECT users.id as userId ,users.firstname as firstName,users.lastname as lastName,users.email as email,user_company.status as companyStatus FROM users inner join user_company on user_company.user_id = users.id WHERE user_company.company_id = :companyId ;")
    @RegisterBeanMapper(UserConfigDTO.class)
    List<UserConfigDTO> getUsersByCompanyId(@Bind("companyId") int companyId);

    @SqlUpdate("INSERT INTO user_company (company_id,user_id) VALUES (:companyId,:userId);")
    void AssignCompanyToUser(@Bind("companyId") int companyId,@Bind("userId") int userId);

}
