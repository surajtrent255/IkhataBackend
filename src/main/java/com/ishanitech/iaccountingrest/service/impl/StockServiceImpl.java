package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.ProductDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDetailDAO;
import com.ishanitech.iaccountingrest.dao.StockDAO;
import com.ishanitech.iaccountingrest.dto.ProductDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.StockDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final DbService dbService;

    @Override
    public List<StockDTO> getAllStocks() {
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        return stockDAO.getAllStocks();
    }

    @Override
    public StockDTO getStockById(int id) {
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        return stockDAO.getStockById(id);
    }

    @Override
    public Integer addNewStock(StockDTO stockDTO) {
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        return stockDAO.addNewStock(stockDTO);
    }

    @Override
    public void updateStock(StockDTO stockDTO, int id) {
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        stockDTO.setUpdateDate(new Date());
        stockDAO.updateStock(stockDTO, id);

    }

    @Override
    public void deleteStock(int id) {
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        stockDAO.deleteStockById(id);

    }

    @Transactional
    @Override
    public void updateStockByCompAndProdId(StockDTO stockDTO) {
        PurchaseBillDTO purchaseBillDTO = new PurchaseBillDTO();
        purchaseBillDTO.setDate(new Date());
        purchaseBillDTO.setUserId(1);
        purchaseBillDTO.setCompanyId(1);
        purchaseBillDTO.setSellerId(1);
        purchaseBillDTO.setStatus(true);

        PurchaseBillDetailDTO purchaseBillDetailDTO= new PurchaseBillDetailDTO();
        purchaseBillDetailDTO.setDate(new Date());
        purchaseBillDetailDTO.setQty(stockDTO.getQty());
        purchaseBillDetailDTO.setCompanyId(1);
        purchaseBillDetailDTO.setProductId(stockDTO.getProductId());

        ProductDTO p = dbService.getDao(ProductDAO.class).getProductById(stockDTO.getProductId());
        purchaseBillDetailDTO.setRate(p.getSellingPrice());
        purchaseBillDetailDTO.setDiscountPerUnit(p.getDiscount());
        int bill_id = dbService.getDao(PurchaseBillDAO.class).addNewPurchaseBill(purchaseBillDTO);
        purchaseBillDetailDTO.setPurchaseBillId(bill_id);
        PurchaseBillDetailDAO purchaseBillDetailDAO = dbService.getDao(PurchaseBillDetailDAO.class);
        purchaseBillDetailDAO.addNewPurchaseInfo(purchaseBillDetailDTO);
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        stockDAO.increaseStockQuantity(stockDTO);
    }
}
