package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BranchDTO;
import com.ishanitech.iaccountingrest.dto.UserBranchDTO;
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
    @SqlUpdate(" INSERT INTO branch (company_id, name, abbrv, description, pan_no, state, zone, district, mun_vdc, ward_no, phone) VALUES ( :companyId, :name, :abbrv, :description, :panNo, :state, :zone, :district, :munVdc, :wardNo, :phone );")
    Integer addBranch(@BindBean BranchDTO branchDTO);

    @SqlQuery("INSERT INTO user_branch("
            + " user_id, branch_id, company_id, status, create_date)"
            + "VALUES ( :userId, :branchId, :companyId, :status, :createDate);")
    @RegisterBeanMapper(UserBranchDTO.class)
    Integer AssignBranchToUser(@BindBean UserBranchDTO userBranchDTO);


    @SqlQuery("SELECT * FROM user_branch WHERE company_id= :companyId AND user_id = :userId")
    @RegisterBeanMapper(UserBranchDTO.class)
    List<UserBranchDTO> getBranchDetailsByCompanyAndUserId(@Bind("companyId") int companyId,@Bind("userId") int userId);





}
