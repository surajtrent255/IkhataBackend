package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.FeatureControlDTO;
import com.ishanitech.iaccountingrest.dto.UserFeatureDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface FeatureControlDAO {

    @SqlQuery("SELECT * FROM feature_control")
    @RegisterBeanMapper(FeatureControlDTO.class)
    List<FeatureControlDTO> getFeatureControls();

    @SqlUpdate("INSERT INTO user_feature( " +
            "  feature_id, user_id, company_id ) " +
            " VALUES (:featureId, :userId, :companyId);")
    void assignFeatureControlToUser(@Bind int featureId,@Bind int userId,@Bind int companyId);


    @SqlQuery(" SELECT u.id as userId,u.firstname as firstName,u.lastname as lastName,u.email as email,u.phone as phone, " +
            " uf.feature_id as featureId, uf.status as status , uf.company_id as companyId, " +
            " f.feature as feature " +
            " FROM users u " +
            " INNER JOIN user_feature uf " +
            " on u.id = uf.user_id " +
            " INNER JOIN feature_control f on f.id = uf.feature_id " +
            " WHERE uf.company_id=:companyId;")
    @RegisterBeanMapper(UserFeatureDTO.class)
    List<UserFeatureDTO> getFeatureControlOfUsersForListing(@Bind int companyId);


    @SqlUpdate("UPDATE user_feature " +
            " SET  status=:status " +
            " WHERE user_id = :userId AND feature_id= :featureId;")
    void enableDisableFeatureControlForUser(@Bind int userId,@Bind boolean status,@Bind int featureId);


    @SqlQuery(" SELECT u.id as userId,u.firstname as firstName,u.lastname as lastName,u.email as email,u.phone as phone, " +
            "             uf.feature_id as featureId, uf.status as status , uf.company_id as companyId,f.feature as feature " +
            "             FROM users u  " +
            "             INNER JOIN user_feature uf " +
            "             INNER JOIN feature_control f on f.id = uf.feature_id  " +
            "             on u.id = uf.user_id " +
            "             WHERE uf.company_id=:companyId AND uf.user_id = :userId;")
    @RegisterBeanMapper(UserFeatureDTO.class)
    List<UserFeatureDTO> getAllFeatureControlOfUserByUserId(@Bind int companyId,@Bind int userId);



    @SqlQuery(" SELECT u.id as userId,u.firstname as firstName,u.lastname as lastName,u.email as email,u.phone as phone, " +
            "             uf.feature_id as featureId, uf.status as status , uf.company_id as companyId,f.feature as feature " +
            "             FROM users u  " +
            "             INNER JOIN user_feature uf " +
            "             INNER JOIN feature_control f on f.id = uf.feature_id  " +
            "             on u.id = uf.user_id " +
            "             WHERE uf.company_id=:companyId AND uf.user_id = :userId AND uf.status = true;")
    @RegisterBeanMapper(UserFeatureDTO.class)
    List<UserFeatureDTO> getFeatureControlDetailsForLocalStorage(@Bind int companyId,@Bind int userId);


}
