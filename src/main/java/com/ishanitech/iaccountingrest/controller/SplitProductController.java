package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.BankDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SplitProductDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BankService;
import com.ishanitech.iaccountingrest.service.SplitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/split")
@RequiredArgsConstructor
public class SplitProductController {
    private final SplitService splitService;
    @GetMapping("/{companyid}/{branchid}")
    public ResponseDTO<?> getAllSplitProduct(@RequestParam("companyid") int companyId , @RequestParam("branchid")  int branchId){
        try{
            return new ResponseDTO<>(splitService.getAllSplitProduct(companyId ,branchId));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(splitService.getAllSplitProduct(companyId,branchId));
    }
    @GetMapping("/{ProductId}")
    public ResponseDTO<?> getAllSplitProductByProductId(@PathVariable("ProductId") int ProductId ){
        try{
            return new ResponseDTO<>(splitService.getAllSplitProductByProductId(ProductId));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(splitService.getAllSplitProductByProductId(ProductId));
    }
    @GetMapping("/id/{Id}")
    public ResponseDTO<?> getSplitProductById(@PathVariable("Id") int Id ){
        try{
            return new ResponseDTO<>(splitService.getSplitProductById(Id));
        }catch (Exception e){
            System.out.println(e);
            log.error(e.getMessage());
        }
        return new ResponseDTO<>(splitService.getSplitProductById(Id));
    }
    @PostMapping()
    public ResponseDTO<?> addsplit(@RequestBody SplitProductDTO SplitProductDTO){
        return  new ResponseDTO<>(splitService.addsplit(SplitProductDTO))  ;
    }
    @PutMapping
    public int updateSplit(@RequestBody SplitProductDTO SplitProductDTO){
        try{
            splitService.updateSplit(SplitProductDTO);
        } catch (Exception e){
            log.error("error while updating splitproduct " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating category");
        }
        return 1;
    }

    @PutMapping("/splitAgain")
    public int updateSplitAgain(@RequestBody SplitProductDTO SplitProductDTO){
        try{
            splitService.updateSplitAgain(SplitProductDTO);
        } catch (Exception e){
            log.error("error while updating splitproductagain " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating splitproduct");
        }
        return 1;
    }
    @PutMapping("/merge")
    public int updateMerge(@RequestBody SplitProductDTO SplitProductDTO){
        try{
            splitService.updateMerge(SplitProductDTO);
        } catch (Exception e){
            log.error("error while updating splitproductagain " + e.getMessage());
            throw new CustomSqlException("Something went wrong while updating splitproduct");
        }
        return 1;
    }



}
