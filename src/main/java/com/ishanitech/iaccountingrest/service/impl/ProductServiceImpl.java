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

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final DbService dbService;
    @Override
    public List<ProductDTO> getAllProducts(int compId, int branchId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        return productDAO.getAllProducts(compId, branchId);
    }

    @Override
    public ProductDTO getProductById(String id, int compId, int branchId, boolean searchByBarCode) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        ProductDTO product ;
        if(searchByBarCode){
            product = productDAO.getProductByBarCode(id, compId, branchId);
        } else{
            product =   productDAO.getProductById(Integer.parseInt(id), compId, branchId);
        }

        return product;
    }

    @Override
    public Integer addNewProduct(ProductDTO product , int stockqtr) {
        if(product.getTax() == 2)
        {
            product.setTaxApproch(1) ;
        } else if  (product.getTax() == 3){
           product.setTaxApproch(2);
        }
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);

        int createdProdId = productDAO.addNewProduct(product);
        StockDTO stockDTO =new StockDTO();
        stockDTO.setProductId(createdProdId);
        stockDTO.setQty(stockqtr);
        stockDTO.setCreateDate(new Date());
        stockDTO.setUpdateDate(new Date());
        stockDTO.setCompanyId(product.getCompanyId());
        stockDTO.setBranchId(product.getBranchId());
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
    public List<InventoryProductsDTO> getAllProductsForInventory(int companyId, int branchId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<InventoryProductsDTO> products = productDAO.getAllProductsForInventoryByUserIdAndCompanyIdAndBranchId(companyId, branchId);
        return products;
    }

    @Override
    public List<ProductDTO> getAllProductsByProductsIds(int[] productsIds) {
        String prodIds = "";
        for(int i=0; i<productsIds.length; i++){
            prodIds += productsIds[i]+",";

        }
        String newProdIds = prodIds.substring(0, prodIds.length()-1);
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<ProductDTO> productDTOS = productDAO.getAllProductsByProductsIds(newProdIds);
        System.out.println(newProdIds);

        return productDTOS;


    }

    @Override
    public List<ProductDTO> getProductsByWildCard(String name, Integer compId, Integer branchId) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<ProductDTO> productDTOS;
        productDTOS = productDAO.getAllProductsByWildCardName(name, compId, branchId);
        return productDTOS;
    }



    @Override
    public  List<ProductDTO> getProductForSearch(Integer compId, Integer branchId, String search) {
        ProductDAO productDAO = dbService.getDao(ProductDAO.class);
        List<ProductDTO> productDTOS;
        productDTOS = productDAO.getProductForSearch( compId, branchId ,search);
        return productDTOS;
    }
}
