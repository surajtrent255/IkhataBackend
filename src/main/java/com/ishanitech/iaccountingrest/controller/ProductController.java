package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.InventoryProductsDTO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseDTO<List<ProductDTO>> getAllProducts(){
        try{
            return new ResponseDTO<List<ProductDTO>>(productService.getAllProducts());
        } catch(Exception e){
            log.error("error occured while fetching products : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching product");
        }
    }


    @GetMapping("/inventory")
    public ResponseDTO<List<InventoryProductsDTO>> getAllProductsForInventory(
//            @RequestParam("userId") int userId ,
            @RequestParam("companyId") int companyId
    ){
        try{
            return new ResponseDTO<List<InventoryProductsDTO>>(productService.getAllProductsForInventory(companyId));
        } catch(Exception e){
            log.error("error occured while fetching products : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching product");
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO<ProductDTO> getProductById(@PathVariable("id") Integer id){
        try{
            return new ResponseDTO<ProductDTO>(productService.getProductById(id));
        } catch(Exception e){
            log.error("error occured while fetching product with id " + id +" "+ e.getMessage());
            throw new CustomSqlException("error occured while fetching product with id " + id +" ");
        }
    }

    @PostMapping
    public ResponseDTO<Integer> addNewProduct(@RequestBody ProductDTO product){
        try{
            System.out.println("entering ********************************");
            return new ResponseDTO<Integer>(productService.addNewProduct(product));
        } catch(Exception ex){
            log.error("error occured while adding product " + ex.getMessage());
            throw new CustomSqlException("error occured while adding product "+ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer id){
        try{
            productService.updateProduct(productDTO,id);
        } catch(Exception e){
            log.error("error occured during product updation with id "+id+" "+e.getMessage());
            throw new CustomSqlException("error occured while updating the product");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        try{
            productService.deleteProduct(id);
        } catch(Exception e){
            log.error("error occured during product deletion with id " + id + " "+ e.getMessage());
            throw new CustomSqlException("error occured while deletig the product");
        }
    }

}
