package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CategoryProductDAO;
import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
import com.ishanitech.iaccountingrest.service.CategoryProductService;
import com.ishanitech.iaccountingrest.service.DbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryProductServiceImpl implements CategoryProductService {

    private final DbService dbService;


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
    public int updateCategoryProduct(CategoryProductDTO categoryProductDTO) {
        CategoryProductDAO categoryProductDAO = dbService.getDao(CategoryProductDAO.class);
        categoryProductDTO.setEditedDate(new Date());
        return categoryProductDAO.updateCategoryProduct(categoryProductDTO, categoryProductDTO.getId());
    }

    @Override
    public void deleteParentandChildCategories(List<Integer> categoryIds) {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        categoryDAO.deleteParentandChildCategories(categoryIds);
    }

    @Override
    public CategoryProductDTO getCategoryByCategoryId(Integer categoryId) {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        CategoryProductDTO category = categoryDAO.getCategoryByCategoryId(categoryId);
        return category;
    }

    @Override
    public List<CategoryProductDTO> getCategoryByParentId(Integer parentId) {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        List<CategoryProductDTO> categories = categoryDAO.getCategoryByParentId(parentId);
        for (CategoryProductDTO cDTO : categories) {
            List<CategoryProductDTO> childCategory = categoryDAO.getCategoryByParentId(cDTO.getId());

        }
        return categories;
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

