package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CompanyLabelInfoDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CompanyLabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("api/v1/company/label")
@RequiredArgsConstructor
public class CompanyLabelController {
    private final CompanyLabelService companyLabelService;


    @GetMapping("/get/label")
    public ResponseDTO<?> getLabel(){
        try{
            return new ResponseDTO<>(companyLabelService.getCompanyLabel());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/{companyId}")
    public ResponseDTO<?> getCompanyLabelInfo(@PathVariable("companyId") int companyId){
        try{
            return new ResponseDTO<>(companyLabelService.getCompanyLabelInfo(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PostMapping("/data")
    public ResponseDTO<String> addCompanyLabelData(@RequestBody CompanyLabelInfoDTO companyLabelInfoDTO){
        try
        {
            companyLabelService.addLabelData(companyLabelInfoDTO);
            return new ResponseDTO<>("Company Label Data is Successfully Added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO<String> addCompanyLabel(@RequestParam("name") String name){
        try
        {
            companyLabelService.addLabel(name);
            return new ResponseDTO<>("Company Label  is Successfully Added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO<String> deleteLabel(@PathVariable("id") int id){
        try
        {
            companyLabelService.deleteLabel(id);
            return new ResponseDTO<>("label Successfully added");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
