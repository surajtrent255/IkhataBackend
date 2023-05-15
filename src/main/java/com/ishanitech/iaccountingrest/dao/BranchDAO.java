package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BranchConfigDTO;
import com.ishanitech.iaccountingrest.dto.BranchDTO;
import com.ishanitech.iaccountingrest.dto.UserBranchDTO;
import com.ishanitech.iaccountingrest.dto.UserCommonConfigDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BranchDAO {
    @SqlQuery("SELECT * FROM branch WHERE company_id = :companyId")
    @RegisterBeanMapper(BranchDTO.class)
    List<BranchDTO> getBranchByCompanyId(@Bind("companyId") int companyId);

    @GetGeneratedKeys
    @SqlUpdate(" INSERT INTO branch (company_id, name, abbrv, description,  state, district, mun_vdc, ward_no, phone) VALUES ( :companyId, :name, :abbrv, :description,  :state, :district, :munVdc, :wardNo, :phone );")
    Integer addBranch(@BindBean BranchDTO branchDTO);

    @SqlUpdate("INSERT INTO user_branch("
            + " user_id, branch_id, company_id, status, create_date)"
            + "VALUES ( :userId, :branchId, :companyId, :status, :createDate);")
    @Nullable
    @RegisterBeanMapper(UserBranchDTO.class)
    Integer AssignBranchToUser(@BindBean UserBranchDTO userBranchDTO);


    @SqlQuery("SELECT users.id as userId, users.firstname as firstName, users.lastname as lastName, "
            + " users.email as email, users.phone as userPhone, "
            + " branch.id as branchId, branch.name as branchName, branch.phone as branchPhone, branch.district as branchDistrict, "
            + " user_branch.status as branchStatus "
            +   " FROM users"
            + " INNER JOIN user_branch ON user_branch.user_id = users.id"
            + " INNER JOIN branch ON branch.id = user_branch.branch_id WHERE user_branch.company_id = :companyId  ")
    @RegisterBeanMapper(BranchConfigDTO.class)
    List<BranchConfigDTO> getBranchUsersByCompanyId(@Bind int companyId);
//  AND users.id  NOT IN (SELECT user_id FROM user_branch)

//for Local Storage
    @SqlQuery("SELECT * FROM user_branch WHERE company_id= :companyId AND user_id = :userId AND status=true")
    @RegisterBeanMapper(UserBranchDTO.class)
    List<UserBranchDTO> getBranchDetailsByCompanyAndUserId(@Bind("companyId") int companyId,@Bind("userId") int userId);


    @SqlUpdate("UPDATE user_branch SET status = :status WHERE user_id=:userId AND company_id = :companyId AND branch_id= :branchId")
    void enableDisableBranchUser(@Bind boolean status,@Bind int userId, @Bind int companyId,@Bind int branchId);

    @SqlQuery("SELECT users.id as userId ,users.firstname as firstName," +
            "users.lastname as lastName,users.email as email," +
            "user_company.status as companyStatus FROM users " +
            " inner join user_company on user_company.user_id = users.id " +
            " WHERE user_company.company_id = :companyId  " +
            " AND users.id  NOT IN (SELECT user_id FROM user_branch) ;")
    @RegisterBeanMapper(UserCommonConfigDTO.class)
    List<UserCommonConfigDTO> getUserForAssignBranchList(@Bind("companyId") int companyId);



}
