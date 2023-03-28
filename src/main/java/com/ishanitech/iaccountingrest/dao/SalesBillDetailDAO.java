package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillInvoiceDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface SalesBillDetailDAO {
    @SqlQuery("SELECT sbd.id as id, sbd.product_id as product_id, sbd.qty as qty, sbd.date as date, sbd.rate as rate, sbd.bill_id as bill_id, sbd.company_id as company_id from sales_bill_detail sbd " +
            " where id = :id")
    @RegisterBeanMapper(SalesBillDetailDTO.class)
    SalesBillDetailDTO getSingleSaleInfoById(int id);


    @SqlBatch("INSERT INTO sales_bill_detail (product_id, qty, discount_per_unit, rate, bill_id, company_id) " +
            " VALUES (:productId, :qty, :discountPerUnit, :rate, :billId, :companyId)")
     void addNewSalesInfo(@BindBean List<SalesBillDetailDTO> salesBillDetailDTO);

        @SqlQuery("SELECT sbd.id as id, sbd.product_id as product_id, sbd.qty as qty, sbd.date as date, sbd.rate as rate, sbd.discount_per_unit as discount_per_unit, sbd.bill_id as bill_id, sbd.company_id as company_id from sales_bill_detail sbd " +
                " where bill_id = :billId and company_id = :companyId")
    @RegisterBeanMapper(SalesBillDetailDTO.class)
    List<SalesBillDetailDTO> getSalesInfoByBillId(int billId, int companyId);
}
