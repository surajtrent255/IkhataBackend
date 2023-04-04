package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CategoryProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryProduct {

    private final CategoryProductService categoryProductService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseDTO<?> getAllCategories(@RequestParam("compId") int compId){
        List<CategoryProductDTO> categories;
        try{
            categories = categoryProductService.getAllCategoriesByCompId(compId);
            return new ResponseDTO<>(categories);
        } catch (Exception e){
            log.error("error occured during fetching catgories :  " + e.getMessage());
            throw new CustomSqlException("Error occured during fetching categories");
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/parentId/{id}")
    public ResponseDTO<?> getCategoriesByParentId(@PathVariable int id){
        List<CategoryProductDTO> categoryProductDTOS;
        try{
            categoryProductDTOS = categoryProductService.getCategoryByParentId(id);
            return new ResponseDTO<>(categoryProductDTOS);
        } catch(Exception ex){
            log.error("error occured during fetching categories : "+ ex.getMessage());
            throw new CustomSqlException("Error occured during fetching category by parentId");
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/single/{id}")
    public ResponseDTO<?> getCategoryByCategoryId(@PathVariable int id){
        CategoryProductDTO categoryProductDTO;
        try{
            categoryProductDTO = categoryProductService.getCategoryByCategoryId(id);
            return new ResponseDTO<>(categoryProductDTO);
        } catch(Exception ex){
            log.error("error occured during fetching categories : "+ ex.getMessage());
            throw new CustomSqlException("Error occured during fething category by cateogoryId");
        }
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO<?> addCategory(@RequestBody CategoryProductDTO categoryDTO) {
        Integer result = null;
        categoryDTO.setCreatedDate(new Date());
        try {
            result = categoryProductService.addCategory(categoryDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomSqlException("Something went wrong while adding category!");
        }
        return new ResponseDTO<>("category created with id " + result);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateCategoryProduct(@RequestBody CategoryProductDTO categoryProductDTO){
        try{
            categoryProductService.updateCategoryProduct(categoryProductDTO);
        } catch (Exception e){
            log.error("error while updating categoryproduct " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating category");
        }
    }


    @DeleteMapping("/{id}")
    public void deleteCategoryProduct(@PathVariable("id") int id){
        try{
            categoryProductService.deleteCategory(id);

        } catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("Something went wrong while deleting category");
        }
    }



}
