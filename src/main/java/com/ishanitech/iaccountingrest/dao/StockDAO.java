package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.StockDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StockDAO {

    @SqlQuery("select s.id AS id, s.product_id as product_id, s.qty as qty, s.company_id as company_id, s.branch_id as branch_id, s.create_date as create_date," +
            " s.update_date as update_date, s.deleted as deleted from stock s ;")
    @RegisterBeanMapper(StockDTO.class)
    List<StockDTO> getAllStocks();

    @SqlQuery("select s.id AS id, s.product_id as product_id, s.qty as qty, s.company_id as company_id, s.branch_id, s.create_date as create_date," +
            " s.update_date as update_date, s.deleted as deleted from stock s where s.id = :id;")
    @RegisterBeanMapper(StockDTO.class)
    StockDTO getStockById(int id);

    @SqlUpdate("UPDATE stock set deleted = true WHERE id = :id;")
    void deleteStockById(int id);

    @SqlUpdate("update stock set product_id = :productId, qty = :qty, company_id = :companyId, branch_id = :branchId, update_date = :updateDate where id = :id;")
    void updateStock(@BindBean StockDTO stockDTO, int id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO stock(product_id, qty, company_id, branch_id)" +
            " VALUES (:productId, :qty, :companyId, :branchId)")
    Integer addNewStock(@BindBean StockDTO stockDTO);

    @SqlUpdate("update stock set qty = qty - :qty where product_id = :productId and company_id = :companyId and branch_id = :branchId ")
    void increaseStockQuantityWithAttrs(@Bind int productId, @Bind int companyId, @Bind int branchId,  @Bind int qty);
    @SqlUpdate("update stock set qty = qty + :qty where product_id = :productId and company_id = :companyId and branch_id = :branchId")
    void increaseStockQuantity(@BindBean StockDTO stockDTO);

    @SqlUpdate("update stock set qty = qty - :qty where product_id = :productId and company_id = :companyId  and branch_id = :branchId")
    void decreaseStockQuantity(@Bind int productId, @Bind int companyId, @Bind int branchId,  @Bind int qty);

    @SqlUpdate("update stock set qty = 0 where qty < 0 and product_id = :productId and company_id = :companyId and branch_id = :branchId")
    void makeStockQuantityToZero(@Bind int productId, @Bind int companyId, @Bind int branchId);

    @Transactional
    default void decreaseTheStockQuantity( int productId,  int companyId, int branchId,  int qty){
        decreaseStockQuantity(  productId,   companyId, branchId,  qty);
//        makeStockQuantityToZero( productId, companyId);
    }

    @SqlUpdate("update stock set qty = qty + :qty where product_id = :productId and company_id = :companyId  and branch_id = :branchId")
    void increaseStockQuantity(@Bind int productId, @Bind int companyId, @Bind int branchId,  @Bind int qty);

    @SqlUpdate("UPDATE stock set deleted = true WHERE product_id = :id;")
    void deleteStockByProductId(int id);
    @SqlQuery("select s.id AS id, s.product_id as product_id, s.qty as qty, s.company_id as company_id, s.branch_id, s.create_date as create_date," +
            " s.update_date as update_date, s.deleted as deleted from stock s where s.product_id = :productId;")
    @RegisterBeanMapper(StockDTO.class)
    StockDTO getStockBYProductId(int productId);


    @SqlUpdate("""
            update stock set qty = qty - :newStockDataForParent where product_id = :id
            
            """)
    void updateStockWhileSplitAgainForParent(int newStockDataForParent, int id);

    @SqlUpdate("""
            update stock set qty = qty + :newStockDataForParent where product_id = :id
            
            """)
    void updateStockWhileMergeAgainForParent(int newStockDataForParent, int id);
    @SqlUpdate("""
            update stock set qty = qty + :newStockDataForChild where product_id = :id
            
            """)
    void updateStockWhileSplitAgainForChild(int newStockDataForChild, int id);

}
