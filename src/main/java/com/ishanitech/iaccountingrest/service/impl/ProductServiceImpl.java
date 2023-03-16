package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.ProductDAO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final DbService dbService;
    @Override
    public List<ProductDTO> getAllProducts() {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        return productDAO.getAllProducts();
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        return productDAO.getProductById(id);
    }

    @Override
    public Integer addNewProduct(ProductDTO product) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);

        return productDAO.addNewProduct(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO , Integer id) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        productDTO.setUpdateDate(new Date());
         productDAO.updateProduct(productDTO, id);
    }

    @Override
    public void deleteProduct(int id) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        productDAO.deleteProduct(id);
    }
}
