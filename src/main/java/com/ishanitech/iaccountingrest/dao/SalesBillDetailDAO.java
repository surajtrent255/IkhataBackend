package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailWithProdInfoDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface SalesBillDetailDAO {
    @SqlQuery("SELECT sbd.id as id, sbd.product_id as product_id, sbd.qty as qty, sbd.date as date, sbd.rate as rate, sbd.bill_id as bill_id, sbd.company_id as company_id from sales_bill_detail sbd " +
            " where id = :id")
    @RegisterBeanMapper(SalesBillDetailDTO.class)
    SalesBillDetailDTO getSingleSaleInfoById(int id);


    @SqlBatch("INSERT INTO sales_bill_detail (product_id, qty, discount_per_unit, date,  rate, tax_rate,  bill_id, company_id, branch_id ) " +
            " VALUES (:productId, :qty, :discountPerUnit, :date, :rate, :taxRate, :billId, :companyId, :branchId)")
     void addNewSalesInfo(@BindBean List<SalesBillDetailDTO> salesBillDetailDTO);

        @SqlQuery("SELECT sbd.id as id, sbd.product_id as product_id, sbd.qty as qty, sbd.date as date, sbd.rate as rate, sbd.discount_per_unit as discount_per_unit, sbd.bill_id as bill_id, sbd.company_id as company_id from sales_bill_detail sbd " +
                " where bill_id = :billId and company_id = :companyId")
    @RegisterBeanMapper(SalesBillDetailDTO.class)
    List<SalesBillDetailDTO> getSalesInfoByBillId(int billId, int companyId);

    @SqlQuery("SELECT sbd.id as id, sbd.product_id as product_id, sbd.qty as qty, sbd.date as date, sbd.rate as rate, " +
            " sbd.discount_per_unit as discount_per_unit, sbd.bill_id as bill_id, sbd.company_id as company_id, " +
            " p.name as product_name  from sales_bill_detail sbd " +
            " inner join product p on p.id = sbd.product_id" +
            " where sbd.bill_id = :billId")
    @RegisterBeanMapper(SalesBillDetailWithProdInfoDTO.class)
    List<SalesBillDetailWithProdInfoDTO> getSalesInfoWithProdNameByBillId(int billId);
}
