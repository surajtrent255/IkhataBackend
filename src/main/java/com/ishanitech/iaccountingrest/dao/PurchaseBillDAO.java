package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PurchaseBillDAO {

    @SqlQuery("SELECT pb.id as id, pb.date as date, pb.user_id as user_id," +
            " pb.company_id as company_id, pb.seller_id as seller_id from purchase_bill pb where pb.status = true")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getAllPurchaseBills();

    @SqlQuery("SELECT pb.id as id, pb.date as date, pb.user_id as user_id," +
            " pb.company_id as company_id, pb.seller_id as seller_id from purchase_bill pb WHERE pb.status = true AND pb.id = :id")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    PurchaseBillDTO getSinglePurchaseBill(Integer id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO purchase_bill (" +
            " user_id, company_id, seller_id" +
            ") VALUES (" +
            ":userId, :companyId, :sellerId " +
            ")")
    Integer addNewPurchaseBill(@BindBean PurchaseBillDTO purchaseBill);

    @SqlUpdate("UPDATE purchase_bill SET " +
            " user_id = :userId, company_id = :companyId, seller_id = :sellerId WHERE id = :id"
          )
    void updatePurchaseBill(@BindBean PurchaseBillDTO productDTO, Integer id);

    @SqlUpdate("UPDATE purchase_bill SET status = false WHERE id = :id")
    void deletePurchaseBill(int id);
}
