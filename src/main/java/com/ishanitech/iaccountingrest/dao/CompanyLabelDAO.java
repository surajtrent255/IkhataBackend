package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CompanyAndUserCompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyLabelInfoDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface CompanyLabelDAO {


    @SqlQuery("""
            Select cld.id as companyLabelDataId, cld.label_id as LabelId, cld.text as text,cld.company_id as companyId,
                               cl.id as companyLabelId, cl.name as name
                               FROM company_label_data cld
                               INNER JOIN company_label cl
                               ON cl.id = cld.label_id
                               WHERE cld.company_id = :companyId
            """)
    @RegisterBeanMapper(CompanyLabelInfoDTO.class)
    List<CompanyLabelInfoDTO> getCompanyLabelInfo(@Bind int companyId) ;


    @SqlQuery("""
            Select id as companyLabelId, name as name from company_label
            """)
    @RegisterBeanMapper(CompanyLabelInfoDTO.class)
    List<CompanyLabelInfoDTO> getCompanyLabel();

    @SqlUpdate("""
            INSERT INTO company_label(
            	 name)
            	VALUES (:name);
            """)
    void addLabel(@Bind String name);

    @SqlUpdate("""
            INSERT INTO company_label_data(
            	 label_id, text, company_id)
            	VALUES (:labelId, :text, :companyId);
            """)
    void addLabelData(@BindBean CompanyLabelInfoDTO companyLabelInfoDTO);

    @SqlUpdate("""
            DELETE FROM company_label WHERE id=:id
            """)
    void deleteLabel(@Bind int id);

}
