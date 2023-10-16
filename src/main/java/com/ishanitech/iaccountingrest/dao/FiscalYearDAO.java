package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.FiscalYearInfo;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface FiscalYearDAO {
    @GetGeneratedKeys
    @SqlUpdate("""
            INSERT INTO fiscal_year (fiscal_year, first_quarter, second_quarter, third_quarter, fourth_quarter, active)
            VALUES (:fiscalYear, :firstQuarter, :secondQuarter, :thirdQuarter, :fourthQuarter, :active);
                        
            """)
    int createNewFiscalYear(@BindBean FiscalYearInfo fiscalYearInfo);


    @SqlQuery(" SELECT * FROM fiscal_year where active = true")
    @RegisterBeanMapper(FiscalYearInfo.class)
    FiscalYearInfo getCurrentFiscalYearInfo();
}
