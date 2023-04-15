package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.InventoryProductsDTO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ProductDAO {

    @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, " +
            " p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date" +
            " as update_date, p.barcode as barcode, p.discount as discount, p.tax as tax FROM product p WHERE p.deleted = false and p.company_id = :compId and p.branch_id = :branchId" )
    @RegisterBeanMapper(ProductDTO.class)
    List<ProductDTO> getAllProducts(@Bind int compId, @Bind int branchId);

    @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id,  p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, " +
            " p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date" +
            " as update_date, p.barcode as barcode, p.discount as discount, p.tax as tax FROM product p WHERE p.id = :id AND p.deleted = false " )
    @RegisterBeanMapper(ProductDTO.class)
    ProductDTO getProductById(Integer id );

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO product (name, description, selling_price, cost_price, user_id, company_id, branch_id, seller_id, category_id, barcode, discount )" +
            "VALUES ( :name, :description, :sellingPrice, :costPrice, :userId, :companyId, :branchId, :sellerId, :categoryId, :barcode, :discount)")
    Integer addNewProduct(@BindBean ProductDTO product);

    @SqlUpdate("UPDATE  product SET name = :name, description = :description, selling_price = :sellingPrice, cost_price = :costPrice, user_id = :userId, " +
            " company_id = :companyId, branch_id = :branchId, seller_id = :sellerId, category_id = :categoryId, update_date = :updateDate," +
            " barcode = :barcode, discount = :discount, tax = :tax where id = :id")
    void updateProduct(@BindBean ProductDTO productDTO, @Bind Integer id);

    @SqlUpdate("UPDATE product SET deleted = true where id = :id")
    void deleteProduct(@Bind int id);


@SqlQuery("select p.id as product_id, p.name as product_name, c.name as category_name," +
        " p.barcode as barcode , s.qty as stock_qty" +
        " from product p inner join category c on p.category_id = c.id" +
        " inner join stock s on p.id = s.product_id" +
        " where p.company_id = :companyId and p.branch_id = :branchId and s.deleted=false and p.deleted = false;")
    @RegisterBeanMapper(InventoryProductsDTO.class)
    List<InventoryProductsDTO> getAllProductsForInventoryByUserIdAndCompanyIdAndBranchId(int companyId, int branchId);


    @SqlQuery("""
             SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id,  
             p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date
             as update_date, p.barcode as barcode, p.discount as discount, p.tax as tax FROM product p WHERE p.deleted = false and p.id in (<newProdIds>) 
             """ )
    @RegisterBeanMapper(ProductDTO.class)
    List<ProductDTO> getAllProductsByProductsIds(@Define String newProdIds);
}
