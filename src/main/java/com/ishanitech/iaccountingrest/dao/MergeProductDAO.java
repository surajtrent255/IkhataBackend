package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.MergeProductDTO;
import com.ishanitech.iaccountingrest.dto.SplitProductDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface MergeProductDAO {
    @SqlQuery("SELECT * FROM merge_product where company_id=:companyId AND branch_id=:branchId")
    @RegisterBeanMapper(MergeProductDTO.class)
    List<MergeProductDTO> getAllMergeProduct(int companyId, int branchId);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO merge_product("
            + "product_id, "
            + "product_name, "
            + "split_product_id, "
            + "split_product_name, "
            + "price, "
            + "qty, "
            + "merge_qty, "
            + "unit, "
            + "tax, "
            + "company_id, "
            + "branch_id) "
            + "VALUES ("
            + ":productId, "
            + ":productName, "
            + ":splitProductId, "
            + ":splitProductName, "
            + ":price, "
            + ":qty, "
            + ":mergeQty, "
            + ":unit, "
            + ":tax, "
            + ":companyId, "
            + ":branchId)")
    int addMerge(@BindBean MergeProductDTO mergeProductDTO);
    @SqlUpdate("UPDATE merge_product SET "
            + "product_id = :productId, "
            + "product_name = :productName, "
            + "split_product_id = :splitProductId, "
            + "split_product_name = :splitProductName, "
            + "price = :price, "
            + "qty = :qty, "
            + "merge_qty = :mergeQty, "
            + "unit = :unit, "
            + "tax = :tax, "
            + "company_id = :companyId, "
            + "branch_id = :branchId "
            + "WHERE id = :id")
    int updateMerge(@BindBean MergeProductDTO mergeProductDTO,@BindBean Integer id);
}
