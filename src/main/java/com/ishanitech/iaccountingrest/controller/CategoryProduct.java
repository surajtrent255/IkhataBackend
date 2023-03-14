package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CategoryProductDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CategoryProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("product/category")
@RequiredArgsConstructor
public class CategoryProduct {

    private CategoryProductService categoryProductService;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseDTO<?> addCategory(@RequestBody CategoryProductDTO categoryDTO) {
        Integer result = null;
        try {
            result = categoryProductService.addCategory(categoryDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomSqlException("Something went wrong while adding category!");
        }
        return new ResponseDTO<>(result);

    }

}
