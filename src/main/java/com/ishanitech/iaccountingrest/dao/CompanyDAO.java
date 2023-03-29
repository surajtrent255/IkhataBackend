package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
import com.ishanitech.iaccountingrest.dto.CompanyAndUserCompany;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyWithUserIdDTO;
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
    @SqlUpdate(" insert into company( name, description, pan_no, state, zone, district, mun_vdc, ward_no, phone) values ( :name, :description, :panNo, :state, :zone, :district, :munVdc, :wardNo, :phone)")
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

    @SqlQuery("select * from company  where id = :Id ")
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getCompanyById(@Bind("Id") int Id );

    @SqlQuery("select c.company_id as companyId,c.name as name ,c.description as description ,c.pan_no as panNo,c.state as state , c.zone as zone , c.district as district, c.mun_vdc as munVdc, c.ward_no as wardNo,c.phone as phone ,u.user_id as userId from company c  inner join user_company u on u.company_id = c.company_id where u.user_id = :userId ")
    @RegisterBeanMapper(CompanyAndUserCompany.class)
    List<CompanyAndUserCompany> getCompanyByUserId(@Bind("userId") int userId );


}
