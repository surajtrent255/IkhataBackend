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
    public void deleteCategory(Integer categoryId, Integer compId, Integer branchId) {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        List<CategoryProductDTO> categories = new ArrayList<>();

        findSubChildCategories(categoryId, categories, categoryDAO);
        StringBuilder catIds = new StringBuilder();
        catIds.append("(").append(categoryId).append(",");

        for(CategoryProductDTO c: categories){
            catIds.append(c.getId()).append(",");
        }

        if (catIds.length() > 1) {
            catIds.deleteCharAt(catIds.length() -1);
        }
        catIds.append(")");

//        int[]  catIds = categoryIds.stream().mapToInt(Integer::intValue).toArray();


        categoryDAO.deleteParentandChildCategories(catIds.toString());
//        categoryDAO.deleteCategory(categoryId, compId, branchId);
//        List<CategoryProductDTO> category = categoryDAO.getAllCategoriesByCompIdAndBranchId(compId, branchId);

    }

    void findSubChildCategories(int id, List<CategoryProductDTO> categories, CategoryProductDAO categoryDAO){
        List<CategoryProductDTO> categoryList = categoryDAO.getCategoryByParentId(id);
        categories.addAll(categoryList);
        categoryList.forEach(cl->{
            findSubChildCategories(cl.getId(), categories, categoryDAO);
        });
        System.out.println(" ");
    }
    @Override
    public List<CategoryProductDTO> getAllCategoriesByCompIdAndBranchId(int compId, int branchId) {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        List<CategoryProductDTO> categories = categoryDAO.getAllCategoriesByCompIdAndBranchId(compId, branchId);

        categories.sort((category1, category2) -> {
            return category1.getParentId() - category2.getParentId();
        });

        //Arranging in hierarchy
        List<CategoryProductDTO> categoryListHierarchy = cleanCategoryCreator(categories);
        log.info("category hiearchy >>" +categoryListHierarchy.toString());
        return categoryListHierarchy;
    }

    @Override
    public int updateCategoryProduct(CategoryProductDTO categoryProductDTO) {
        CategoryProductDAO categoryProductDAO = dbService.getDao(CategoryProductDAO.class);
        categoryProductDTO.setEditedDate(new Date());
        return categoryProductDAO.updateCategoryProduct(categoryProductDTO, categoryProductDTO.getId());
    }


    @Override
    public CategoryProductDTO getCategoryByCategoryId(Integer categoryId, Integer companyId, Integer branchId) {
        CategoryProductDAO categoryDAO = dbService.getDao(CategoryProductDAO.class);
        CategoryProductDTO category = categoryDAO.getCategoryByCategoryId(categoryId, companyId, branchId);
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
                childCategory.setDeleted(p.isDeleted());
                // no need to set ChildrenItems list because the constructor created a new empty list

                // ------ Parent ----
                //MegaMenuDTO mmdParent ;

                if (categoryMap.containsKey(p.getParentId())) {
                    parentCategory = categoryMap.get(p.getParentId());
                    parentCategory.setId(parentCategory.getId());
                    parentCategory.setParentId(parentCategory.getParentId());
                    parentCategory.setDeleted(parentCategory.isDeleted());
                } else {
                    //mmdParent = new MegaMenuDTO();
                    parentCategory = new CategoryProductDTO();
                    parentCategory.setId(p.getId());
                    parentCategory.setParentId(p.getParentId());
                    parentCategory.setDeleted(p.isDeleted());
                    categoryMap.put(p.getParentId(), parentCategory);
                }

                parentCategory.addChildCategory(childCategory);
            } else {
                categoryMap.put(p.getId(), new CategoryProductDTO(p.getId(), p.getName(), p.getParentId(), p.isDeleted()));
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

