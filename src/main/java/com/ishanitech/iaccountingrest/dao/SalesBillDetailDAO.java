package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface SalesBillDetailDAO {

    @SqlQuery("SELECT sbd.id as id, sbd.product_id as product_id, sbd.qty as qty, sbd.date as date, sbd.rate as rate, sbd.bill_id as bill_id, sbd.company_id as company_id from sales_bill_detail sbd WHERE deleted = false")
    @RegisterBeanMapper(SalesBillDetailDTO.class)
    List<SalesBillDetailDTO> getAllSalesInfo();

    @SqlQuery("SELECT sbd.id as id, sbd.product_id as product_id, sbd.qty as qty, sbd.date as date, sbd.rate as rate, sbd.bill_id as bill_id, sbd.company_id as company_id from sales_bill_detail sbd WHERE deleted = false" +
            " and id = :id")
    @RegisterBeanMapper(SalesBillDetailDTO.class)
    SalesBillDetailDTO getSingleSaleInfoById(int id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO sales_bill_detail (product_id, qty, discount, rate, bill_id, company_id) " +
            " VALUES (:productId, :qty, :discount, :rate, :billId, :companyId)")
    int addNewSalesInfo(@BindBean SalesBillDetailDTO salesBillDetailDTO);

    @SqlUpdate("UPDATE sales_bill_detail SET deleted = true WHERE id = :id")
    void deleteSalesById(int id);

    @SqlUpdate("UPDATE sales_bill_detail set product_id = :productId, qty = :qty, discount = :discount, rate = :rate, bill_id = :billId," +
            " company_id = :companyId WHERE id = :id")
    void editSalesById(@BindBean SalesBillDetailDTO salesBillDetailDTO, int id);
}
