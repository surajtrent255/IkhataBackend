package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CompanyLabelInfoDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;

import java.util.List;

public interface CompanyLabelService {
    List<CompanyLabelInfoDTO> getCompanyLabelInfo( int companyId) ;

    void addLabel( String name);

    void addLabelData( CompanyLabelInfoDTO companyLabelInfoDTO);

    void deleteLabel( int id);

    List<CompanyLabelInfoDTO> getCompanyLabel();
}
