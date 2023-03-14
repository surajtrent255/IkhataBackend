package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CategoryProductDAO;
import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
import com.ishanitech.iaccountingrest.service.CategoryProductService;
import com.ishanitech.iaccountingrest.service.DbService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CategoryProductServiceImpl implements CategoryProductService {

    private DbService dbService;


    @Override
    public Integer addCategory(CategoryProductDTO categoryproductDTO) {
        CategoryProductDAO categoryProductDAO = dbService.getDao(CategoryProductDAO.class);
        return categoryProductDAO.addCategory(categoryproductDTO);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        categoryDAO.deleteCategory(categoryId);
    }

    @Override
    public List<CategoryProductDTO> getAllCategories() {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        List<CategoryProductDTO> categories = categoryDAO.getAllCategories();

        categories.sort((category1, category2) -> {
            return category1.getParentId() - category2.getParentId();
        });

        //Arranging in hierarchy
        List<CategoryProductDTO> categoryListHierarchy = cleanCategoryCreator(categories);

        return categoryListHierarchy;
    }

    @Override
    public void deleteParentandChildCategories(List<Integer> categoryIds) {

    }

    public List<CategoryProductDTO> cleanCategoryCreator(List<CategoryProductDTO> categoryDTOS) {
        Map<Integer, CategoryProductDTO> categoryMap = new HashMap<>();

        // you are using MegaMenuDTO as Linked list with next and before link

        // populate a Map
        for (CategoryProductDTO p : categoryDTOS) {
            CategoryProductDTO parentCategory;
            CategoryProductDTO childCategory;
            if (p.getParentId() != 0) {
                //  ----- Child -----

                if (categoryMap.containsKey(p.getId())) {
                    childCategory = categoryMap.get(p.getId());
                } else {
                    childCategory = new CategoryProductDTO();
                    categoryMap.put(p.getId(), childCategory);
                }
                childCategory.setId(p.getId());
                childCategory.setParentId(p.getParentId());
                childCategory.setName(p.getName());
                // no need to set ChildrenItems list because the constructor created a new empty list

                // ------ Parent ----
                //MegaMenuDTO mmdParent ;

                if (categoryMap.containsKey(p.getParentId())) {
                    parentCategory = categoryMap.get(p.getParentId());
                    parentCategory.setId(parentCategory.getId());
                    parentCategory.setParentId(parentCategory.getParentId());
                } else {
                    //mmdParent = new MegaMenuDTO();
                    parentCategory = new CategoryProductDTO();
                    parentCategory.setId(p.getId());
                    parentCategory.setParentId(p.getParentId());
                    categoryMap.put(p.getParentId(), parentCategory);
                }

                parentCategory.addChildCategory(childCategory);
            } else {
                categoryMap.put(p.getId(), new CategoryProductDTO(p.getId(), p.getName(), p.getParentId()));
            }


        }
        List<CategoryProductDTO> finalHierarchicalCategory = new ArrayList<CategoryProductDTO>();
        for(CategoryProductDTO mmd : categoryMap.values()){
            if(mmd.getParentId() == 0)
                finalHierarchicalCategory.add(mmd);
        }

        return finalHierarchicalCategory;
    }
}

