package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyWithUserIdDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {


    private final CompanyService companyService;


    @PostMapping("/add")
    public ResponseDTO<?> addCompany(@RequestBody CompanyWithUserIdDTO companyWithUserIdDTO) {
        int userId = companyWithUserIdDTO.getUserId();
        CompanyDTO companyDTO = companyWithUserIdDTO.getCompanyDTO();
        Integer result = null;
        try {
            result = companyService.addCompany(companyDTO,userId);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomSqlException("Something went wrong while adding Company!");
        }
        return new ResponseDTO<>( "Company is Successfully Added");

    }

    @GetMapping
    public ResponseDTO<List<CompanyDTO>> getAllCompanyList(){
        return new ResponseDTO<List<CompanyDTO>>(companyService.getAllCompanyList());
    }

    @GetMapping("/{id}")
    public ResponseDTO<?> getCompanyById(@PathVariable("Id") int Id){
        return new ResponseDTO<>(companyService.getCompanyById(Id));
    }

    @GetMapping("/userCompany/{userId}")
    public ResponseDTO<?> getCompanyByUserId(@PathVariable("userId") int userId){
        return new ResponseDTO<>(companyService.getCompanyByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") int id){
        companyService.deleteCompany(id);
    }


}
