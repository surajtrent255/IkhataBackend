package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PurchaseBillDAO {

    @SqlQuery(
            "   select b.id as id, b.date as date, b.user_id as user_id, b.cust_id as cust_id, b.company_id as company_id," +
                    "  b.status as status, b.invoice_no as invoice_no, b.fiscal_year as fiscal_year, b.total as total, b.tax as tax," +
                    "  b.discount as discount, b.grand_total as grand_total FROM purchase_bill b WHERE status = true")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getAllPurchaseBills();

    @SqlQuery(
            "   select b.id as id, b.date as date, b.user_id as user_id, b.cust_id as cust_id, b.company_id as company_id," +
                    "  b.status as status, b.invoice_no as invoice_no, b.fiscal_year as fiscal_year, b.total as total, b.tax as tax," +
                    "  b.discount as discount, b.grand_total as grand_total FROM purchase_bill b WHERE status = true and id = :id")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    PurchaseBillDTO getSinglePurchaseBill(Integer id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO purchase_bill (user_id, cust_id, company_id, invoice_no, fiscal_year, total, tax, discount, grand_total)" +
            " VALUES (:userId, :custId, :companyId, :invoiceNo, :fiscalYear, :total, :tax, :discount, :grandTotal)")
    Integer addNewPurchaseBill(@BindBean PurchaseBillDTO purchaseBill);


    @SqlUpdate("UPDATE purchase_bill SET status = false WHERE id = :id")
    void deletePurchaseBill(int id);
}
