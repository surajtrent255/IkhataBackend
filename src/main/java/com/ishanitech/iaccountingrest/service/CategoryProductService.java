package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;

import java.util.List;

public interface CategoryProductService {
    Integer addCategory(CategoryProductDTO categoryproductDTO);
    void deleteCategory(Integer categoryId);
    List<CategoryProductDTO> getAllCategoriesByCompIdAndBranchId(int compId, int branchId);
    int updateCategoryProduct(CategoryProductDTO categoryProductDTO);
    void deleteParentandChildCategories(List<Integer> categoryIds);
    CategoryProductDTO getCategoryByCategoryId(Integer categoryId);

    List<CategoryProductDTO> getCategoryByParentId(Integer parentId);
}
