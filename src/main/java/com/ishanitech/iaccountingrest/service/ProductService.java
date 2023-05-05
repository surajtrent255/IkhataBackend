package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.InventoryProductsDTO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts(int comId, int branchId);

    ProductDTO getProductById(Integer id, int compId, int branchId);

    Integer addNewProduct(ProductDTO product ,int stockqtr);

    void updateProduct(ProductDTO productDTO, Integer id);

    void deleteProduct(int id);

    List<InventoryProductsDTO> getAllProductsForInventory( int companyId, int branchId);

    List<ProductDTO> getAllProductsByProductsIds(int[] productsIds);
}
