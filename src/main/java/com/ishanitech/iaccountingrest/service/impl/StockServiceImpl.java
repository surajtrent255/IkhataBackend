package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.StockDAO;
import com.ishanitech.iaccountingrest.dto.StockDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
