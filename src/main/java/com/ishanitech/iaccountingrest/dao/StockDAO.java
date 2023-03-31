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

    @SqlQuery("select s.id AS id, s.product_id as product_id, s.qty as qty, s.company_id as company_id, s.create_date as create_date," +
            " s.update_date as update_date, s.deleted as deleted from stock s ;")
    @RegisterBeanMapper(StockDTO.class)
    List<StockDTO> getAllStocks();

    @SqlQuery("select s.id AS id, s.product_id as product_id, s.qty as qty, s.company_id as company_id, s.create_date as create_date," +
            " s.update_date as update_date, s.deleted as deleted from stock s where s.id = :id;")
    @RegisterBeanMapper(StockDTO.class)
    StockDTO getStockById(int id);

    @SqlUpdate("UPDATE stock set deleted = true WHERE id = :id;")
    void deleteStockById(int id);

    @SqlUpdate("update stock set product_id = :productId, qty = :qty, company_id = :companyId, update_date = :updateDate where id = :id;")
    void updateStock(@BindBean StockDTO stockDTO, int id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO stock(product_id, qty, company_id)" +
            " VALUES (:productId, :qty, :companyId)")
    Integer addNewStock(@BindBean StockDTO stockDTO);

    @SqlUpdate("update stock set qty = qty + :qty where product_id = :productId and company_id = :companyId ")
    void increaseStockQuantity(@BindBean StockDTO stockDTO);

    @SqlUpdate("update stock set qty = qty - :qty where product_id = :productId and company_id = :companyId ")
    void decreaseStockQuantity(@Bind int productId, @Bind int companyId, @Bind int qty);

    @SqlUpdate("update stock set qty = 0 where qty < 0 and product_id = :productId and company_id = :companyId")
    void makeStockQuantityToZero(@Bind int productId, @Bind int companyId);

    @Transactional
    default void decreaseTheStockQuantity( int productId,  int companyId, int qty){
        decreaseStockQuantity(  productId,   companyId,  qty);
        makeStockQuantityToZero( productId, companyId);
    }
}
