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
                        INSERT INTO fiscal_year (fiscal_year, first_quarter_start, first_quarter_end, second_quarter_start,
                         second_quarter_end, third_quarter_start, third_quarter_end, active)
                        VALUES (:fiscalYear, :firstQuarterStart, :firstQuarterEnd, :secondQuarterStart, :secondQuarterEnd,
                        :thirdQuarterStart, :thirdQuarterEnd,
                        :active);

                        """)
        int createNewFiscalYear(@BindBean FiscalYearInfo fiscalYearInfo);

        @SqlQuery(" SELECT * FROM fiscal_year where active = true")
        @RegisterBeanMapper(FiscalYearInfo.class)
        FiscalYearInfo getCurrentFiscalYearInfo();

        @SqlQuery("""
                        SELECT * FROM fiscal_year WHERE fiscal_year = :fiscalYear
                        """)
        @RegisterBeanMapper(FiscalYearInfo.class)
        FiscalYearInfo getFiscalYear(String fiscalYear);
}
