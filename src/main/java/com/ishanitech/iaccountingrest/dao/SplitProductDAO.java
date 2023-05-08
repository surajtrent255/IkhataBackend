package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.MergeProductDTO;
import com.ishanitech.iaccountingrest.dto.SplitProductDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface SplitProductDAO {

    @SqlQuery("SELECT * FROM split_product where company_id=:companyId AND branch_id=:branchId")
    @RegisterBeanMapper(SplitProductDTO.class)
    List<SplitProductDTO> getAllSplitProduct(int companyId, int branchId);


    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO split_product("
            + "product_id, "
            + "product_name, "
            + "qty, "
            + "split_qty, "
            + "total_qty, "
            + "unit, "
            + "price, "
            +"updated_product_id,"
            + "updated_product_name, "
            + "company_id, "
            + "branch_id) "
            + "VALUES ("
            + ":productId, "
            + ":productName, "
            + ":qty, "
            + ":splitQty, "
            + ":totalQty, "
            + ":unit, "
            + ":price, "
            +":updatedProductId,"
            + ":updatedProductName, "
            + ":companyId, "
            + ":branchId)")
    int addsplit( @BindBean SplitProductDTO splitProductDTO);

    @SqlUpdate("UPDATE split_product SET "
            + "product_id = :productId, "
            + "product_name = :productName, "
            + "qty = :qty, "
            + "split_qty = :splitQty, "
            + "total_qty = :totalQty, "
            + "unit = :unit, "
            + "price = :price, "
            +" updated_product_id = :updatedProductId,"
            + "updated_product_name = :updatedProductName, "
            + "company_id = :companyId, "
            + "branch_id = :branchId "
            + "WHERE id = :id")

    int updateSplit(@BindBean SplitProductDTO splitProductDTO,@BindBean int id);
    @SqlQuery("SELECT * FROM split_product where product_id=:productId")
    @RegisterBeanMapper(SplitProductDTO.class)
    List<SplitProductDTO> getAllSplitProductByProductId(int productId);
    @SqlQuery("SELECT * FROM split_product where id=:id")
    @RegisterBeanMapper(SplitProductDTO.class)
    List<SplitProductDTO> getSplitProductById(int id);
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO split_product("
            + "product_id, "
            + "product_name, "
            + "qty, "
            + "split_qty, "
            + "total_qty, "
            + "unit, "
            + "price, "
            +"updated_product_id,"
            + "updated_product_name, "
            + "company_id, "
            + "branch_id) "
            + "VALUES ("
            + ":productId, "
            + ":productName, "
            + ":qty, "
            + ":splitQty, "
            + ":totalQty, "
            + ":unit, "
            + ":price, "
            +":updatedProductId,"
            + ":updatedProductName, "
            + ":companyId, "
            + ":branchId)")
    void addsplitlog(SplitProductDTO splitProductDTO);
}
