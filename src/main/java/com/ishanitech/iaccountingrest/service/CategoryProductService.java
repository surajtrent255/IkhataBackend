package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;

import java.util.List;

public interface CategoryProductService {
    Integer addCategory(CategoryProductDTO categoryproductDTO);
    void deleteCategory(Integer categoryId);
    List<CategoryProductDTO> getAllCategories();
    void deleteParentandChildCategories(List<Integer> categoryIds);
}
