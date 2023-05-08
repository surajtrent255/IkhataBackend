package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.BankDAO;
import com.ishanitech.iaccountingrest.dao.SplitProductDAO;
import com.ishanitech.iaccountingrest.dao.StockDAO;
import com.ishanitech.iaccountingrest.dto.SplitProductDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.SplitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class SplitServiceImpl implements SplitService {
    private final DbService dbService;

    @Override
    public List<SplitProductDTO> getAllSplitProduct(int companyId, int branchId) {
        SplitProductDAO SplitProductDAO = dbService.getDao(SplitProductDAO.class);

        return SplitProductDAO.getAllSplitProduct(companyId,branchId);
    }

    @Override
    public int addsplit(SplitProductDTO splitProductDTO) {
        SplitProductDAO SplitProductDAO = dbService.getDao(SplitProductDAO.class);
        int Id =0;


        try{

            Id = SplitProductDAO.addsplit(splitProductDTO);
            return Id;
        } catch(JdbiException jdbiException) {

            log.error("error occured while fetching products : " + jdbiException.getMessage());
            throw new CustomSqlException("Error occured while adding bank");

        }
    }



    @Override
    public int updateSplit(SplitProductDTO splitProductDTO) {
        SplitProductDAO SplitProductDAO = dbService.getDao(SplitProductDAO.class);
        return SplitProductDAO.updateSplit(splitProductDTO, splitProductDTO.getId());
    }

    @Override
    public List<SplitProductDTO> getAllSplitProductByProductId(int productId) {
        SplitProductDAO SplitProductDAO = dbService.getDao(SplitProductDAO.class);

        return SplitProductDAO.getAllSplitProductByProductId(productId);
    }

    @Override
    public List<SplitProductDTO> getSplitProductById(int id) {
        SplitProductDAO SplitProductDAO = dbService.getDao(SplitProductDAO.class);

        return SplitProductDAO.getSplitProductById(id);
    }

    @Transactional
    @Override
    public void updateSplitAgain(SplitProductDTO splitProductDTO) {
        StockDAO stockDAO = dbService.getDao(StockDAO.class);
        Integer newStockDataForParent = splitProductDTO.getSplitQty();
        Integer newStockDataForChild = splitProductDTO.getSplitQty() * splitProductDTO.getQty();
        stockDAO.updateStockWhileSplitAgainForParent(newStockDataForParent, splitProductDTO.getProductId());
        stockDAO.updateStockWhileSplitAgainForChild(newStockDataForChild, splitProductDTO.getUpdatedProductId());
    }

    @Transactional
    @Override
    public void updateMerge(SplitProductDTO splitProductDTO) {
        StockDAO stockDAO = dbService.getDao(StockDAO.class);

        Integer newStockDataForParent = splitProductDTO.getSplitQty();
//        Integer remainder = splitProductDTO.getSplitQty() - newStockDataForParent*splitProductDTO.getQty();
        Integer newStockDataForChild = - splitProductDTO.getSplitQty()*splitProductDTO.getQty();
        stockDAO.updateStockWhileMergeAgainForParent(newStockDataForParent, splitProductDTO.getProductId());
        stockDAO.updateStockWhileSplitAgainForChild(newStockDataForChild, splitProductDTO.getUpdatedProductId());
    }




}
