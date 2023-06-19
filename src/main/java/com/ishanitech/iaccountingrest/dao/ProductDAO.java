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

        @SqlQuery("SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, "
                        +
                        " p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date"
                        +
                        " as update_date, p.barcode as barcode, p.tax_approch as taxApproach, p.discount as discount, p.tax as tax ,p.unit as unit,p.quantity_per_unit as qtyPerUnit FROM product p WHERE p.deleted = false and p.company_id = :compId and p.branch_id = :branchId")
        @RegisterBeanMapper(ProductDTO.class)
        List<ProductDTO> getAllProducts(@Bind int compId, @Bind int branchId);

        @SqlQuery("""
                        SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id,  p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id,
                        p.is_average_price as averagePriceStatus,
                        p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, s.qty as stock, p.update_date
                        as update_date, p.rate_percentage as ratePercentage, p.tax_approch as taxApproach, p.barcode as barcode, p.discount as discount, p.tax as tax,p.unit as unit ,p.quantity_per_unit as qtyPerUnit FROM product p
                        inner join stock s on p.id = s.product_id WHERE p.id = :id AND p.company_id = :compId AND p.branch_id = :branchId AND p.deleted = false """)
        @RegisterBeanMapper(ProductDTO.class)
        ProductDTO getProductById(Integer id, int compId, int branchId);

        @GetGeneratedKeys
        @SqlUpdate("INSERT INTO product (name, description, selling_price, cost_price, user_id, company_id, branch_id, seller_id, category_id, barcode, discount ,tax,tax_approch,unit ,quantity_per_unit , product_type , rate_percentage ,is_average_price)"
                        + "VALUES ( :name, :description, :sellingPrice, :costPrice, :userId, :companyId, :branchId, :sellerId, :categoryId, :barcode, :discount ,:tax,:taxApproach,:unit ,:qtyPerUnit ,:productType , :ratePercentage ,:averagePriceStatus)")
        Integer addNewProduct(@BindBean ProductDTO product);

        @SqlUpdate("UPDATE  product SET name = :name, description = :description, selling_price = :sellingPrice, cost_price = :costPrice, user_id = :userId, "
                        +
                        " company_id = :companyId, branch_id = :branchId, seller_id = :sellerId, category_id = :categoryId, update_date = :updateDate,"
                        +
                        " barcode = :barcode, discount = :discount, tax = :tax , unit= :unit   where id = :id")
        void updateProduct(@BindBean ProductDTO productDTO, @Bind Integer id);

        @SqlUpdate("UPDATE product SET deleted = true where id = :id")
        void deleteProduct(@Bind int id);

        @SqlQuery("select p.id as product_id, p.name as product_name, c.name as category_name," +
                        " p.barcode as barcode , s.qty as stock_qty," +
                        " p.rate_percentage as ratePercentage,"+
                        "  p.is_average_price as averagePriceStatus,"+
                        " from product p inner join category c on p.category_id = c.id" +
                        " inner join stock s on p.id = s.product_id" +
                        " where p.company_id = :companyId and p.branch_id = :branchId and s.deleted=false and p.deleted = false;")
        @RegisterBeanMapper(InventoryProductsDTO.class)
        List<InventoryProductsDTO> getAllProductsForInventoryByUserIdAndCompanyIdAndBranchId(int companyId,
                        int branchId);

        @SqlQuery("""
                        SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, s.qty as stock,
                         p.is_average_price as averagePriceStatus, p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date
                        as update_date, p.barcode as barcode, p.tax_approch as taxApproach, p.discount as discount, p.tax as tax  ,p.unit as unit FROM product p inner join stock s on p.id = s.product_id WHERE p.deleted = false and p.id in (<newProdIds>)
                        """)
        @RegisterBeanMapper(ProductDTO.class)
        List<ProductDTO> getAllProductsByProductsIds(@Define String newProdIds);

        @SqlQuery("""
                        SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, s.qty as stock,
                        p.rate_percentage as ratePercentage,  p.is_average_price as averagePriceStatus,
                        p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date
                        as update_date, p.barcode as barcode, p.discount as discount, p.tax as tax FROM product p inner join stock s on p.id = s.product_id  WHERE LOWER(p.name) like LOWER(:name) and p.deleted = false  and p.company_id = :compId and
                        p.branch_id = :branchId
                        """)
        @RegisterBeanMapper(ProductDTO.class)
        List<ProductDTO> getAllProductsByWildCardName(String name, Integer compId, Integer branchId);

        @SqlQuery("""
                        SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id,  p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id,
                        p.is_average_price as averagePriceStatus,
                        p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, s.qty as stock, p.update_date
                        as update_date, p.rate_percentage as ratePercentage, p.tax_approch as taxApproach, p.barcode as barcode, p.discount as discount, p.tax as tax,p.unit as unit ,p.quantity_per_unit as qtyPerUnit FROM product p
                        inner join stock s on p.id = s.product_id WHERE p.barcode = :id AND p.company_id = :compId AND p.branch_id = :branchId AND p.deleted = false """)
        @RegisterBeanMapper(ProductDTO.class)
        ProductDTO getProductByBarCode(String id, int compId, int branchId);

        @SqlQuery("""
                        SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id, p.selling_price as selling_price, p.cost_price as cost_price, p.user_id as user_id, s.qty as stock,
                        p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date  as update_date, p.barcode as barcode, p.discount as discount,
                        p.is_average_price as averagePriceStatus,
                        p.tax as tax FROM product p inner join stock s on p.id = s.product_id WHERE LOWER(p.name) like LOWER(:search) and p.deleted = false  and p.company_id = :compId and
                        p.branch_id = :branchId
                        """)
        @RegisterBeanMapper(ProductDTO.class)
        List<ProductDTO> getProductForSearch(@Bind int compId, @Bind int branchId, @Bind String search);

        @SqlQuery("""
        SELECT p.id as id, p.name as name, p.description as description, p.branch_id as branch_id, p.selling_price as selling_price, p.cost_price as cost_price, s.qty as stock,
        p.user_id as user_id, p.company_id as company_id, p.seller_id as seller_id, p.category_id as category_id, p.create_date as create_date, p.update_date
        as update_date, p.barcode as barcode, p.tax_approch as taxApproach, p.discount as discount, p.tax as tax ,p.unit as unit,p.quantity_per_unit as qtyPerUnit
        FROM product p inner join stock s on p.id = s.product_id WHERE p.deleted = false and p.company_id = :compId and p.branch_id = :branchId <caseQuery> ; 
        """)
        @RegisterBeanMapper(ProductDTO.class)
        List<ProductDTO> getLimitedProductsForInventoryByUserIdAndCompanyIdAndBranchId(@Define String caseQuery, Integer compId, Integer branchId);

        @SqlQuery("""
    SELECT p.id as id,
        p.name as name,
        p.description as description,
        p.branch_id as branch_id,
        p.selling_price as selling_price,
        p.cost_price as cost_price,
        s.qty as stock,
        p.user_id as user_id,
        p.company_id as company_id,
        p.seller_id as seller_id,
        p.category_id as category_id,
        p.create_date as create_date,
        p.update_date as update_date,
        p.barcode as barcode,
        p.tax_approch as taxApproach,
        p.discount as discount,
        p.tax as tax,
        p.unit as unit,
        p.quantity_per_unit as
        qtyPerUnit
    FROM
        product p
        inner join
        stock s
        on p.id=
        s.product_id WHERE p.deleted=false <caseQuery>;""")

        @RegisterBeanMapper(ProductDTO.class)
        List<ProductDTO> getLimitedProducts(@Define String caseQuery);
}
