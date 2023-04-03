package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PurchaseBillDetailDAO {

    @SqlBatch("INSERT INTO purchase_bill_detail (product_id, qty, discount_per_unit, rate, purchase_bill_id, company_id) " +
            " VALUES (:productId, :qty, :discountPerUnit, :rate, :purchaseBillId, :companyId)")
    void addNewPurchaseInfo(@BindBean List<PurchaseBillDetailDTO> purchaseBillDetailDTOS);

    @SqlQuery("SELECT pbd.id as id, pbd.product_id as product_id, pbd.qty as qty, pbd.date as date, pbd.rate as rate, pbd.bill_id as bill_id, pbd.company_id as company_id from purchase_bill_detail pbd " +
            " where bill_id = :id")
    @RegisterBeanMapper(PurchaseBillDetailDTO.class)
    PurchaseBillDetailDTO getSinglePurchaseInfoById(int id);
}
