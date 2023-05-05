package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.MergeProductDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SplitProductDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.MergeProductService;
import com.ishanitech.iaccountingrest.service.SplitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/merge")
@RequiredArgsConstructor
public class MergeProductController {

    private final MergeProductService MergeProductService;
    @GetMapping("/{companyid}/{branchid}")
    public ResponseDTO<?> getAllMergeProduct(@RequestParam("companyid") int companyId , @RequestParam("branchid")  int branchId){
        try{
            return new ResponseDTO<>(MergeProductService.getAllMergeProduct(companyId ,branchId));
        }catch (Exception e){
            System.out.println(e);
            log.error("error while GET merge product " +e.getMessage());
        }
        return new ResponseDTO<>(MergeProductService.getAllMergeProduct(companyId,branchId));
    }
    @PostMapping()
    public ResponseDTO<?> addsplit(@RequestBody MergeProductDTO MergeProductDTO){
        return  new ResponseDTO<>(MergeProductService.addMerge(MergeProductDTO))  ;
    }
    @PutMapping
    public int updateSplit(@RequestBody MergeProductDTO MergeProductDTO){
        try{
            MergeProductService.updateMerge(MergeProductDTO);
        } catch (Exception e){
            log.error("error while updating merge product " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updatin merge product");
        }
        return 1;
    }


}
