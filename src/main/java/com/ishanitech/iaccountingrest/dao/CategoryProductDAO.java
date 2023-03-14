package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface CategoryProductDAO {

    @GetGeneratedKeys
    @SqlUpdate(" insert into category(name, description, parent_id, user_id, company_id) values (:name, :description, :parent_id, :user_id, :company_id)")
    Integer addCategory(@BindBean CategoryProductDTO categoryDTO);

    @SqlUpdate("Update category set deleted = true where category_id = :categoryId")
     void deleteCategory(@Bind("categoryId") Integer categoryId);

    @SqlQuery("SELECT * FROM category WHERE deleted = false")
    List<CategoryProductDTO> getAllCategories();
}

