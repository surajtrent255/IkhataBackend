package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.InventoryProductsDTO;
import com.ishanitech.iaccountingrest.dto.PaginationTypeDTO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts(int comId, int branchId);

    ProductDTO getProductById(String id, int compId, int branchId, boolean searchByBarCode);

    ProductDTO  addNewProduct(ProductDTO product ,int stockqtr);

    void updateProduct(ProductDTO productDTO, Integer id);

    void deleteProduct(int id);

    List<InventoryProductsDTO> getAllProductsForInventory( int companyId, int branchId);

    List<ProductDTO> getAllProductsByProductsIds(int[] productsIds);

    List<ProductDTO> getProductsByWildCard(String name, Integer compId, Integer branchId);

//    ProductDTO getProductForSearch(int compId, int branchId, String search);

    List<ProductDTO> getProductForSearch(Integer compId, Integer branchId, String search);

    List<ProductDTO> getLimitedProducts(Integer offset, Integer pageTotalItems, String searchBy, String searchWildCard, String sortBy, Integer compId, Integer branchId);
}
