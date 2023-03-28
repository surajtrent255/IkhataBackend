package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
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

    @SqlQuery("SELECT * FROM company")
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getAllCompanyList();

    @SqlQuery("select * from company where company_id = :companyId")
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getCompanyByName(@Bind("companyId") int companyId );


}
