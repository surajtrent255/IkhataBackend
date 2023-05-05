package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.StockDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.StockDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/stock")
@RestController
public class StockController {
    private final StockService stockService;

    @GetMapping
    public ResponseDTO<List<StockDTO>> getAllStocks(){
        try{
            return new ResponseDTO<List<StockDTO>>(stockService.getAllStocks());
        } catch(Exception e) {
            log.error("Error occured accessing the stock infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the stock infos : " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO<StockDTO> getStockById(@PathVariable int id){
        try{
            return new ResponseDTO<StockDTO>(stockService.getStockById(id));
        } catch(Exception e) {
            log.error("Error occured accessing the stock info : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the stock info : " + e.getMessage());
        }
    }
    @GetMapping("/product/{productId}")
    public ResponseDTO<StockDTO> getStockByProductId(@PathVariable int productId){
        try{
            return new ResponseDTO<StockDTO>(stockService.getStockBYProductId(productId));
        } catch(Exception e) {
            log.error("Error occured accessing the stock info : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the stock info : " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseDTO<Integer> addNewStock(@RequestBody StockDTO stockDTO){
        try{
            return new ResponseDTO<Integer>(stockService.addNewStock(stockDTO));
        } catch(Exception e) {
            log.error("Error occured creating the stock info : " + e.getMessage());
            throw new CustomSqlException("Error occured creating the stock info : " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updateStock(@RequestBody StockDTO stockDTO, @PathVariable int id){
        try{
            stockService.updateStock(stockDTO, id);
        } catch(Exception e) {
            log.error("Error occured updating the stock info : " + e.getMessage());
            throw new CustomSqlException("Error occured updating the stock info :" + e.getMessage());
        }
    }

    @PutMapping
    public void updateStockByCompAndProdId(@RequestBody StockDTO stockDTO){
        try{
            stockService.updateStockByCompAndProdId(stockDTO);
        } catch(Exception e) {
            log.error("Error occured updating the stock info : " + e.getMessage());
            throw new CustomSqlException("Error occured updating the stock info :" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable("id") int id){
        try{
            stockService.deleteStock(id);
        } catch(Exception ex){
            log.error("Error occured deleting the stock info " + ex.getMessage());
            throw new CustomSqlException("Error occured deleting the stock info : " + ex.getMessage());
        }
    }

}






