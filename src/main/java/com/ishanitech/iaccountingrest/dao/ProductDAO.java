package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.ProductDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ProductDAO {

    @SqlQuery("SELECT p.id as id, p.name as name, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, " +
            " p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date" +
            " as update_date, p.barcode as barcode, p.discount as discount, p.tax as tax FROM product p WHERE p.deleted = false" )
    @RegisterBeanMapper(ProductDTO.class)
    List<ProductDTO> getAllProducts();

    @SqlQuery("SELECT p.id as id, p.name as name, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, " +
            " p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date" +
            " as update_date, p.barcode as barcode, p.discount as discount, p.tax as tax FROM product p WHERE p.id = :id AND p.deleted = false" )
    @RegisterBeanMapper(ProductDTO.class)
    ProductDTO getProductById(Integer id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO product (name, selling_price, cost_price, user_id, company_id, seller_id, category_id, barcode, discount, tax )" +
            "VALUES ( :name, :sellingPrice, :costPrice, :userId, :companyId, :sellerId, :categoryId, :barcode, :discount, :tax)")
    Integer addNewProduct(@BindBean ProductDTO product);

    @SqlUpdate("UPDATE  product SET name = :name, selling_price = :sellingPrice, cost_price = :costPrice, user_id = :userId, " +
            " company_id = :companyId, seller_id = :sellerId, category_id = :categoryId, update_date = :updateDate," +
            " barcode = :barcode, discount = :discount, tax = :tax where id = :id")
    void updateProduct(@BindBean ProductDTO productDTO, @Bind Integer id);

    @SqlUpdate("UPDATE product SET deleted = true where id = :id")
    void deleteProduct(@Bind int id);
}
