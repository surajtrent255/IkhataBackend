package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.UserCompanyDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.UserCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/company")
@RequiredArgsConstructor
public class UserCompanyController {

    private final UserCompanyService userCompanyService;


    @PostMapping("/add")
    public ResponseDTO<?> addUserCompany(@RequestBody UserCompanyDTO userCompanyDTO) {
        Integer result = null;
        try {
            result = userCompanyService.addUserCompany(userCompanyDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomSqlException("Something went wrong while adding User's Company!");
        }
        return new ResponseDTO<>( "User's Company is Successfully Added");

    }

    @GetMapping
    public ResponseDTO<List<UserCompanyDTO>> getAllUserCompanyList(){
        return new ResponseDTO<List<UserCompanyDTO>>(userCompanyService.getAllUserCompanyList());
    }

    @GetMapping("/{id}")
    public ResponseDTO<?> getUserCompanyById(@PathVariable("id") int id){
        return new ResponseDTO<>(userCompanyService.getUserCompanyById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseDTO<?> getUserCompanyByUserId(@PathVariable("id") int id){
        return new ResponseDTO<>(userCompanyService.getUserCompanyById(id));
    }


    @DeleteMapping("/{id}")
    public void deleteUserCompany(@PathVariable("id") int id){
        userCompanyService.deleteUserCompany(id);
    }



}
