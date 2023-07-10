package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyLogoDTO;
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
    @SqlUpdate("""
                INSERT INTO company (
                        name,
                        email,
                        description,
                        pan_no,
                        state,
                        district,
                        mun_vdc,
                        ward_no,
                        phone,
                        customer,
                        owner_name,
                        landline_number,
                        registration_type,
                        created_date,
                        created_date_nepali
                        ) VALUES (
                        :name,
                        :email,
                        :description,
                        :panNo,
                        :state,
                        :district,
                        :munVdc,
                        :wardNo,
                        :phone,
                        :customer,
                        :ownerName,
                        :landlineNumber,
                        :registrationType,
                        :createdDate,
                        :createdDateNepali
                     )                                                                            
            """)
    Integer addCompany(@BindBean CompanyDTO companyDTO);

    @Transaction
    default int addCompanyWithUserId(CompanyDTO companyDTO, int userId) {
        int savedCompanyId = addCompany(companyDTO);
        insertToUserCompany(savedCompanyId, userId);
        return savedCompanyId;
    }

    @SqlUpdate("INSERT INTO user_company (company_id,user_id) VALUES (:savedCompanyId,:userId)")
    void insertToUserCompany(@Bind int savedCompanyId, @Bind int userId);

    @SqlUpdate("delete from company where company_id = :companyId")
    void deleteCompany(@Bind("companyId") Integer companyId);

    @SqlQuery("SELECT * FROM company ")
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getAllCompanyList();

    @SqlQuery("select * from company  where pan_no = :PanNo ")
    @RegisterBeanMapper(CompanyDTO.class)
    CompanyDTO getCompanyByPanNo(@Bind("PanNo") Long PanNo);

    @SqlQuery("""
            SELECT c.company_id AS companyId,
                               c.name AS name,
                               c.description AS description,
                               c.pan_no AS panNo,
                               c.state AS state,
                               c.district AS district,
                               c.mun_vdc AS munVdc,
                               c.ward_no AS wardNo,
                               c.email AS email,
                               c.phone AS phone,
                               c.owner_name as ownerName,
                               c.landline_number as landlineNumber,
                               c.registration_type as registrationType,
                               c.created_date as createdDate,
                               c.created_date_nepali as createdDateNepali,
                        	   cl.image_name as imageName,
                        	   cl.id as imageId
                        FROM company c
                        INNER JOIN company_logo cl ON cl.company_id = c.company_id
                        WHERE c.company_id=:companyId
            """)
    @RegisterBeanMapper(CompanyDTO.class)
    CompanyDTO getCompanyByIdForEdit(@Bind("companyId") int companyId);

    @SqlQuery("""
            SELECT c.company_id AS companyId,
                   c.name AS name,
                   c.description AS description,
                   c.pan_no AS panNo,
                   c.state AS state,
                   c.district AS district,
                   c.mun_vdc AS munVdc,
                   c.ward_no AS wardNo,
                   c.phone AS phone,
                   c.email AS email,
                   c.owner_name as ownerName,
                   c.landline_number as landlineNumber,
                   c.registration_type as registrationType,
                   c.created_date as createdDate,
                   c.created_date_nepali as createdDateNepali,
                   c.is_approved AS isApproved,
                   u.user_id AS userId,
            	   cl.image_name as imageName,
            	   cl.id as imageId
            FROM company c
            INNER JOIN user_company u ON u.company_id = c.company_id
            LEFT JOIN company_logo cl ON cl.company_id = c.company_id
            WHERE u.user_id = :userId
              AND u.status = true
              AND c.customer = false
              AND c.status = true;
            """)
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getCompanyByUserId(@Bind("userId") int userId);

    // suraj
    @SqlQuery("""
            SELECT c.company_id AS companyId,
                   c.name AS name,
                   c.description AS description,
                   c.pan_no AS panNo,
                   c.state AS state,
                   c.district AS district,
                   c.mun_vdc AS munVdc,
                   c.ward_no AS wardNo,
                   c.email AS email,
                   c.phone AS phone,
                   c.owner_name as ownerName,
                   c.landline_number as landlineNumber,
                   c.registration_type as registrationType,
                   c.created_date as createdDate,
                   c.created_date_nepali as createdDateNepali
            FROM company c
            WHERE c.company_id = :compId AND c.deleted = false;
            """)
    @RegisterBeanMapper(CompanyDTO.class)
    CompanyDTO getCompanyByCompanyId(@Bind("compId") int compId);

    @SqlQuery("select * from company  where phone = :customerPhoneorPan or pan_no = :customerPhoneorPan ")
    @RegisterBeanMapper(CompanyDTO.class)
    List<CompanyDTO> getCompanyByPhoneNo(long customerPhoneorPan);

    @SqlUpdate(" UPDATE public.company " +
            " SET  status= :status " +
            " WHERE company_id = :companyId; ")
    void updateCompanyStatus(@Bind boolean status, @Bind int companyId);


//    company image
    @SqlUpdate("""
            INSERT INTO company_logo(
            	 image_name,  company_id)
            	VALUES (:imageName, :companyId);
            """)
    void addCompanyLogo(@BindBean CompanyLogoDTO companyLogoDTO);

    @SqlQuery("""
            SELECT * FROM company_logo
              WHERE company_id=:companyId
            """)
    @RegisterBeanMapper(CompanyLogoDTO.class)
    CompanyLogoDTO getCompanyLogo(@Bind int companyId);

    @SqlUpdate("""
            UPDATE company
            SET owner_name = :ownerName,
                landline_number = :landlineNumber,
                registration_type = :registrationType,
                email = :email,
                description = :description,
                state = :state,
                district = :district,
                mun_vdc = :munVdc,
                ward_no = :wardNo,
                phone = :phone
            WHERE company_id = :companyId;      
            """)
    @RegisterBeanMapper(CompanyDTO.class)
    void editCompany(@BindBean CompanyDTO companyDTO);

    @SqlUpdate("""
            UPDATE company_logo
            	SET  image_name=:imageName
            	WHERE company_id = :companyId;
            """)
    void editCompanyLogo(@Bind String imageName,@Bind int companyId);

}
