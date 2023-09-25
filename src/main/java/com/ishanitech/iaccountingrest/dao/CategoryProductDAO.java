package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.AllowUnusedBindings;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface CategoryProductDAO {

    @GetGeneratedKeys
    @SqlUpdate(" insert into category(name, description, parent_id, user_id, company_id, branch_id, create_date) values (:name, :description, :parentId, :userId, :companyId, :branchId, :createdDate)")
    Integer addCategory(@BindBean CategoryProductDTO categoryDTO);

    @SqlUpdate("Update category set deleted = true where id = :categoryId and company_id = :compId and branch_id = :branchId;")
    void deleteCategory(@Bind("categoryId") Integer categoryId, Integer compId, Integer branchId);

    @SqlQuery("SELECT * FROM category WHERE company_id = :compId and branch_id = :branchId")
    @RegisterBeanMapper(CategoryProductDTO.class)
    List<CategoryProductDTO> getAllCategoriesByCompIdAndBranchId(@Bind int compId, @Bind int branchId);

    @SqlUpdate("UPDATE category SET name = :name, "
            + "parent_id = :parentId, description = :description,  user_id = :userId, "
            + "company_id = :companyId, branch_id = :branchId, edit_date = :editedDate WHERE id = :categoryId")
    int updateCategoryProduct(@BindBean CategoryProductDTO categoryProductDTO, @Bind int categoryId);

    @SqlBatch("UPDATE category SET deleted = true WHERE category_id = :categoryIds")
    void deleteParentandChildCategories(List<Integer> categoryIds);

    @SqlQuery("SELECT * FROM category WHERE id = :categoryId AND company_id = :companyId and branch_id = :branchId;")
    @RegisterBeanMapper(CategoryProductDTO.class)
    CategoryProductDTO getCategoryByCategoryId(@Bind("categoryId") Integer categoryId, Integer companyId, Integer branchId);

    @SqlQuery("SELECT * FROM category WHERE parent_id = :parentId;")
    @RegisterBeanMapper(CategoryProductDTO.class)
    List<CategoryProductDTO> getCategoryByParentId(@Bind int parentId);

    @SqlQuery(""" 
            select id from category where lower(name) like lower(:searchWildCard) 
            """)
    int[] getCategoriesIdsByCloseName(@Bind String searchWildCard);
}

