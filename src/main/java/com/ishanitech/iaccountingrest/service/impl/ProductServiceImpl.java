package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.ProductDAO;
import com.ishanitech.iaccountingrest.dao.StockDAO;
import com.ishanitech.iaccountingrest.dto.InventoryProductsDTO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;
import com.ishanitech.iaccountingrest.dto.StockDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final DbService dbService;
    @Override
    public List<ProductDTO> getAllProducts(int compId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        return productDAO.getAllProducts(compId);
    }

    @Override
    public ProductDTO getProductByIdAndCompId(Integer id, int compId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        ProductDTO product =   productDAO.getProductByIdAndCompId(id, compId);
        return product;
    }

    @Override
    public Integer addNewProduct(ProductDTO product) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);

        int createdProdId = productDAO.addNewProduct(product);
        StockDTO stockDTO =new StockDTO();
        stockDTO.setProductId(createdProdId);
        stockDTO.setQty(0);
        stockDTO.setCreateDate(new Date());
        stockDTO.setUpdateDate(new Date());
        stockDTO.setCompanyId(product.getCompanyId());
        dbService.getDao(StockDAO.class).addNewStock(stockDTO);
        return createdProdId;
    }

    @Override
    public void updateProduct(ProductDTO productDTO , Integer id) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        productDTO.setUpdateDate(new Date());
         productDAO.updateProduct(productDTO, id);
    }

    @Transactional
    @Override
    public void deleteProduct(int id) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        productDAO.deleteProduct(id);
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        stockDAO.deleteStockByProductId(id);
    }

    @Override
    public List<InventoryProductsDTO> getAllProductsForInventory(int companyId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<InventoryProductsDTO> products = productDAO.getAllProductsForInventoryByUserIdAndCompanyId(companyId);
        return products;
    }
}
