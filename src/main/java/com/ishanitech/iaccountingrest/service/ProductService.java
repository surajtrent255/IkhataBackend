package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(Integer id);

    Integer addNewProduct(ProductDTO product);

    void updateProduct(ProductDTO productDTO, Integer id);

    void deleteProduct(int id);
}
