package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CompanyAndUserCompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;


public interface CompanyDAO {

    @GetGeneratedKeys
    @SqlUpdate(" insert into company( name, description, pan_no, state,  district, mun_vdc, ward_no, phone, customer) values ( :name, :description, :panNo, :state,  :district, :munVdc, :wardNo, :phone, :customer)")
    Integer addCompany(@BindBean CompanyDTO companyDTO);

    @Transaction
    default int addCompanyWithUserId(CompanyDTO companyDTO,int userId){
        int savedCompanyId = addCompany(companyDTO);
        insertToUserCompany(savedCompanyId,userId);
        return savedCompanyId;
    }

    @SqlUpdate("INSERT INTO user_company (company_id,user_id) VALUES (:savedCompanyId,:userId)")
    void insertToUserCompany(@Bind int savedCompanyId,@Bind int userId);

    @SqlUpdate("delete from company where company_id = :companyId")
    void deleteCompany(@Bind("companyId") Integer companyId);

    @SqlQuery("SELECT * FROM company ")
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getAllCompanyList();

    @SqlQuery("select * from company  where pan_no = :PanNo ")
    @RegisterBeanMapper(CompanyDTO.class)
    CompanyDTO getCompanyByPanNo(@Bind("PanNo") Long PanNo );

    @SqlQuery("select c.company_id as companyId"
            + ",c.name as name"
            + ",c.description as description"
            + ",c.pan_no as panNo "
            + ",c.state as state "
            + ", c.district as district"
            + ", c.mun_vdc as munVdc,"
            + "c.ward_no as wardNo"
            +",c.phone as phone ,"
            + "u.user_id as userId from company c "
            + "inner join user_company u on u.company_id = c.company_id where u.user_id = :userId and  u.status = true  ")
    @RegisterBeanMapper(CompanyAndUserCompanyDTO.class)
    List<CompanyAndUserCompanyDTO> getCompanyByUserId(@Bind("userId") int userId );

//    suraj
    @SqlQuery("select c.company_id as companyId"
            + ",c.name as name"
            + ",c.description as description"
            + ",c.pan_no as panNo "
            + ",c.state as state "
            + ", c.district as district"
            + ", c.mun_vdc as munVdc,"
            + "c.ward_no as wardNo"
            +",c.phone as phone "
            + " from company c "
            + " where c.company_id = :compId  and c.deleted = false;")
    @RegisterBeanMapper(CompanyDTO.class)
   CompanyDTO getCompanyByCompanyId(@Bind("compId") int compId );


}
