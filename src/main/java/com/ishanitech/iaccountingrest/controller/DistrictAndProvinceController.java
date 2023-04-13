package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.DistrictAndProvinceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DistrictAndProvinceController {

    private final DistrictAndProvinceService districtAndProvinceService;

    @GetMapping("/province")
    public ResponseDTO<?> getAllState(){
        try{
            return new ResponseDTO<>(districtAndProvinceService.getAllState());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

    @GetMapping("/district/{provinceId}")
    public ResponseDTO<?> getDistrictByProvinceId(@PathVariable("provinceId") int provinceId){
        try {
            return new ResponseDTO<>(districtAndProvinceService.getDistrictByProvinceId(provinceId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

}
