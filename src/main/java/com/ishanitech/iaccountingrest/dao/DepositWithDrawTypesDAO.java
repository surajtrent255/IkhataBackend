package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.DepositWithdrawTypesDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface DepositWithDrawTypesDAO {

    @SqlQuery("select * from  deposit_withdraw_types")
    @RegisterBeanMapper(DepositWithdrawTypesDTO.class)
    List<DepositWithdrawTypesDTO> getAllDepositWithDrawTypes();
}
