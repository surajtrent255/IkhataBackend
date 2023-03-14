package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.ProductDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;

import java.util.List;

public interface ProductDAO {

    @RegisterBeanMapper(ProductDTO.class)
    List<ProductDTO> getAllProducts();

    @RegisterBeanMapper(ProductDTO.class)
    ProductDTO getProductById(Integer id);

    Integer addNewProduct(ProductDTO product);

    void updateProduct(ProductDTO productDTO, Integer id);

    void deleteProduct(int id);
}
