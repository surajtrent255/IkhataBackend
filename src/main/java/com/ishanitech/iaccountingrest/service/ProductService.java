package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.InventoryProductsDTO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Integer id);

    Integer addNewProduct(ProductDTO product);

    void updateProduct(ProductDTO productDTO, Integer id);

    void deleteProduct(int id);

    List<InventoryProductsDTO> getAllProductsForInventory( int companyId);
}
