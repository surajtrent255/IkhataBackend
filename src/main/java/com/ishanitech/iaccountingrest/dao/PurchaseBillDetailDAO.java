package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailWithProdInfo;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PurchaseBillDetailDAO {

    @SqlBatch("INSERT INTO purchase_bill_detail (product_id, qty, discount_per_unit, rate, purchase_bill_id, company_id, branch_id) " +
            " VALUES (:productId, :qty, :discountPerUnit, :rate, :purchaseBillId, :companyId, :branchId)")
    void addNewPurchaseInfo(@BindBean List<PurchaseBillDetailDTO> purchaseBillDetailDTOS);

    @SqlQuery("SELECT pbd.id as id, pbd.product_id as product_id, pbd.qty as qty, pbd.date as date, pbd.rate as rate, pbd.bill_id as bill_id, pbd.company_id as company_id, pbd.branch_id as branch_id" +
            " from purchase_bill_detail pbd " +
            " where bill_id = :id")
    @RegisterBeanMapper(PurchaseBillDetailDTO.class)
    PurchaseBillDetailDTO getSinglePurchaseInfoById(int id);

    @SqlQuery("""
            SELECT pbd.id as id, pbd.product_id as product_id, pbd.qty as qty, pbd.date as date, pbd.rate as rate, 
                                     pbd.discount_per_unit as discount_per_unit, pbd.purchase_bill_id as purchase_bill_id, pbd.company_id as company_id,
            						 vt.vat_rate_num as taxRate,
                                     p.name as product_name  from purchase_bill_detail pbd 
                                     inner join product p on p.id = pbd.product_id
            						 inner join vat_rate_type vt on p.tax=vt.id
                                     where pbd.purchase_bill_id = :billId and pbd.company_id = :companyId and pbd.branch_id = :branchId;
            """)
    @RegisterBeanMapper(PurchaseBillDetailWithProdInfo.class)
    List<PurchaseBillDetailWithProdInfo> getPurchaseInfoWithProdNameByBillId(int billId, int companyId, int branchId);
}
