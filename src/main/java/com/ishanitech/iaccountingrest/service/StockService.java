package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.StockDTO;

import java.util.List;

public interface StockService {
    List<StockDTO> getAllStocks();

    StockDTO getStockById(int id);

    Integer addNewStock(StockDTO stockDTO);

    void updateStock(StockDTO stockDTO, int id);

    void deleteStock(int id);
}
