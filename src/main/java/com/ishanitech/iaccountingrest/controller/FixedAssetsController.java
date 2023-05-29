package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.FixedAssetsDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.FixedAssetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/fixedAssets")
public class FixedAssetsController {

    private final FixedAssetsService fixedAssetsService;

    @GetMapping
    public ResponseDTO<?> getFixedAssetsDetails(@RequestParam("companyId") int companyId){
        try {
            return new ResponseDTO<>(fixedAssetsService.getFixedAssetsDetails(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/limited")
    public ResponseDTO<List<FixedAssetsDTO>> getLimitedFixedAssetsByCompId(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId){
        try{
            return new ResponseDTO<List<FixedAssetsDTO>>(fixedAssetsService.getLimitedFixedAssetsByCompIdAndBranchId(offset, pageTotalItems, compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
    }

    @GetMapping("/{SN}")
    public ResponseDTO<?> getFixedAssetsDetailsBySN(@PathVariable("SN") int SN){
        try {
            return new ResponseDTO<>(fixedAssetsService.getFixedAssetsBySN(SN));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PutMapping
    public ResponseDTO<?> updateFixedDetails(@RequestBody FixedAssetsDTO fixedAssetsDTO){
        try{
            fixedAssetsService.updateFixedAssets(fixedAssetsDTO);
            return new ResponseDTO<>("Update Successfully");

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PostMapping
    public ResponseDTO<?> addFixedDetails(@RequestBody FixedAssetsDTO fixedAssetsDTO){
        try {
            return new ResponseDTO<>("Data Successfully Added" + fixedAssetsService.addFixedAssetsDetails(fixedAssetsDTO));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @DeleteMapping("/{SN}")
    public ResponseDTO<?> deleteFixedAssets(@PathVariable("SN") int SN){
        try{
            fixedAssetsService.deleteFromAssets(SN);
            return new ResponseDTO<>("Successfully Deleted");
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

}
