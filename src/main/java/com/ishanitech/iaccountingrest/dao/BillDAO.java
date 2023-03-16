package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BillDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BillDAO {
    @SqlQuery("SELECT b.id as id, b.date as date, b.user_id as user_id, b.cust_id as cust_id, b.company_id as company_id "
            + " FROM bill b WHERE deleted = false")
    @RegisterBeanMapper(BillDTO.class)
    List<BillDTO> getAllBills();

    @SqlQuery("SELECT b.id as id, b.date as date, b.user_id as user_id, b.cust_id as cust_id, b.company_id as company_id "
            + " FROM bill b WHERE deleted = false  AND b.id = :id")
    @RegisterBeanMapper(BillDTO.class)
    BillDTO getBillById(int id);

    @SqlUpdate("INSERT INTO bill (user_id, cust_id, company_id) VALUES (:userId, :custId, :companyId)")
    Integer addNewBill(@BindBean BillDTO billDTO);

    @SqlUpdate("UPDATE bill SET user_id = :userId, cust_id = :custId, company_id = :companyId WHERE id = :id")
    void updateBill(@BindBean  BillDTO billDTO, int id);

    @SqlUpdate("UPDATE bill SET deleted = true WHERE id = :id")
    void deleteBillById(int id);
}
