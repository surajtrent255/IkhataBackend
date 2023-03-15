package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.UserCompanyDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface UserCompanyDAO {


    @GetGeneratedKeys
    @SqlUpdate(" insert into user_company( company_id,user_id) values ( :companyId,:userId)")
    Integer addUserCompany(@BindBean UserCompanyDTO userCompanyDTO);

    @SqlUpdate("delete from user_company where id = :id")
    void deleteUserCompany(@Bind("id") Integer id);

    @SqlQuery("SELECT * FROM user_company")
    @RegisterBeanMapper(UserCompanyDTO.class)
    List<UserCompanyDTO> getAllUserCompanyList();

    @SqlQuery("select * from user_company where id = :id")
    @RegisterBeanMapper(UserCompanyDTO.class)
    List<UserCompanyDTO> getUserCompanyById(@Bind("id") int id );



}
