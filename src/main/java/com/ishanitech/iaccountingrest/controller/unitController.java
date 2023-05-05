package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.UnitDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/unit")
@RequiredArgsConstructor
public class unitController {
    private final  UnitService UnitService;



    @GetMapping
    public ResponseDTO<?> getAllunit (){
        try{
            return new ResponseDTO<>(UnitService.getAllUnit());
        } catch(Exception ex){
            log.error("error occured while fetching unit " + ex.getMessage());
            throw new CustomSqlException(" Something went wrong while fetching unit types " + ex.getMessage());
        }
    }

}
