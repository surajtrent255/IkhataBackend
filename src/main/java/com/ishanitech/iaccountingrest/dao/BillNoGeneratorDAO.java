package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BillNoGeneratorDTO;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

public interface BillNoGeneratorDAO {
    
    @SqlUpdate("insert into bill_no_generator" +
            "(" +
            " fiscal_year," +
            " bill_no," +
            " active" +
            ") values " +
            "(" +
            " :fiscalYear," +
            " 1," +
            " true" +
            ")")
    int createNewFiscalYear(@Bind String fiscalYear);

    @SqlUpdate("update bill_no_generator set active = false where id != :id;")
    void disableOtherYears(@Bind int id);

    @Transactional
    default void initilizeBillNoForNewFiscalYear(String fiscalyear){
        int fiscalYearearId = createNewFiscalYear( fiscalyear);
        disableOtherYears(fiscalYearearId);
    }

    @SqlQuery("select bill_no from bill_no_generator where active = true;")
    int getBillNo();

    @SqlUpdate("update bill_no_generator set bill_no = bill_no +  1 where active = true;")
    void increaseBillNo();

    @Transactional
    default int getBillNoForCurrentFiscalYear(){
        int currentBillNo = getBillNo();
        increaseBillNo();
        return currentBillNo;
    }

    @SqlQuery("select bg.fiscal_year from bill_no_generator bg where active = true")
    String getCurrentFiscalYear();

    @SqlQuery("select bng.id as id, bng.fiscal_year as fiscal_year, " +
            " bng.bill_no as bill_no, bng.active as active " +
            " from bill_no_generator bng" +
            " where active=true;")
    @RegisterBeanMapper(BillNoGeneratorDTO.class)
    BillNoGeneratorDTO getBillNoGeneratorEntityByActive();
}
