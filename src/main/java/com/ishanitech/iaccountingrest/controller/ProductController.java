package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.InventoryProductsDTO;
import com.ishanitech.iaccountingrest.dto.PaginationTypeDTO;
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
    public ResponseDTO<List<ProductDTO>> getAllProducts(@RequestParam("compId") int compId,
            @RequestParam("branchId") int branchId) {
        try {
            return new ResponseDTO<List<ProductDTO>>(productService.getAllProducts(compId, branchId));
        } catch (Exception e) {
            log.error("error occured while fetching products : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching product");
        }
    }

    @GetMapping("/limited")
    public ResponseDTO<List<ProductDTO>> getLimitedProducts(@RequestParam("offset") Integer offset,
            @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchWildCard") String searchWildCard,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("compId") Integer compId,
            @RequestParam("branchId") Integer branchId) {
        try {
            return new ResponseDTO<List<ProductDTO>>(productService.getLimitedProducts(offset, pageTotalItems, searchBy,
                    searchWildCard, sortBy, compId, branchId));
        } catch (Exception e) {
            log.error("error occured while fetching products : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching product");
        }
    }

    @GetMapping("/searchByWildCard")
    public ResponseDTO<List<ProductDTO>> getProductsByWildcards(@RequestParam("name") String name,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId) {
        try {
            return new ResponseDTO<List<ProductDTO>>(
                    productService.getProductsByWildCard(name + "%", compId, branchId));
        } catch (Exception e) {
            log.error("error occured while fetching products by wildcard : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching product ");
        }
    }

    @GetMapping("/search")
    public ResponseDTO<List<ProductDTO>> getProductForSearch(@RequestParam("compId") Integer compId,
            @RequestParam("branchId") Integer branchId,
            @RequestParam("search") String search) {
        try {
            return new ResponseDTO<List<ProductDTO>>(
                    productService.getProductForSearch(compId, branchId, "%" + search + "%"));
        } catch (Exception e) {
            log.error("error occured while fetching product with search " + search + " " + e.getMessage());
            throw new CustomSqlException("error occured while fetching product with search " + search + " ");
        }
    }

    @GetMapping("/getProductsByIds")
    public ResponseDTO<List<ProductDTO>> getAllProductsByProductsId(@RequestParam("productsIds") int[] productsIds) {
        try {
            int[] arrProductsIds = productsIds;
            return new ResponseDTO<List<ProductDTO>>(productService.getAllProductsByProductsIds(productsIds));
        } catch (Exception ex) {
            log.error("error during fetching products by ids " + ex.getMessage());
            throw new CustomSqlException("Something went wrong !! ");
        }

    }

    @GetMapping("/inventory")
    public ResponseDTO<List<InventoryProductsDTO>> getAllProductsForInventory(
            // @RequestParam("userId") int userId ,
            @RequestParam("companyId") int companyId, @RequestParam("branchId") int branchId) {
        try {
            return new ResponseDTO<List<InventoryProductsDTO>>(
                    productService.getAllProductsForInventory(companyId, branchId));
        } catch (Exception e) {
            log.error("error occured while fetching products : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching product");
        }
    }

    @GetMapping("/{productId}")
    public ResponseDTO<ProductDTO> getProductById(@PathVariable("productId") String id,
            @RequestParam("compId") int compId, @RequestParam("branchId") int branchId,
            @RequestParam("searchByBarCode") boolean searchByBarCode) {
        try {
            return new ResponseDTO<ProductDTO>(productService.getProductById(id, compId, branchId, searchByBarCode));
        } catch (Exception e) {
            log.error("error occured while fetching product with id " + id + " " + e.getMessage());
            throw new CustomSqlException("error occured while fetching product with id " + id + " ");
        }
    }

    @PostMapping
    public ResponseDTO<ProductDTO> addNewProduct(@RequestBody ProductDTO product,
            @RequestParam("stockqty") int stockqtr) {
        try {
            System.out.println("entering ******************************** " + stockqtr);
            return new ResponseDTO<ProductDTO>(productService.addNewProduct(product, stockqtr));
        } catch (Exception ex) {
            log.error("error occured while adding product " + ex.getMessage());
            throw new CustomSqlException("error occured while adding product " + ex.getMessage() + product);
        }
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer id) {
        try {
            productService.updateProduct(productDTO, id);
        } catch (Exception e) {
            log.error("error occured during product updation with id " + id + " " + e.getMessage());
            throw new CustomSqlException("error occured while updating the product");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            log.error("error occured during product deletion with id " + id + " " + e.getMessage());
            throw new CustomSqlException("error occured while deletig the product");
        }
    }

}
