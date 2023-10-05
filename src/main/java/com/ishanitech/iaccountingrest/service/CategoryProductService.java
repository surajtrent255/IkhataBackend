package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;

import java.util.List;

public interface CategoryProductService {
    Integer addCategory(CategoryProductDTO categoryproductDTO);
    void deleteCategory(Integer categoryId, Integer compId, Integer branchId);
    List<CategoryProductDTO> getAllCategoriesByCompIdAndBranchId(int compId, int branchId);
    int updateCategoryProduct(CategoryProductDTO categoryProductDTO);
    CategoryProductDTO getCategoryByCategoryId(Integer categoryId, Integer compId, Integer branchId);

    List<CategoryProductDTO> getCategoryByParentId(Integer parentId);
}
