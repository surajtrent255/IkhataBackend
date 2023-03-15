package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.SalesBillDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesBillDetailServiceImpl implements SalesBillDetailService {

    private final DbService dbService;

    @Override
    public List<SalesBillDetailDTO> getAllSalesInfo() {
        return dbService.getDao(SalesBillDetailDAO.class).getAllSalesInfo();
    }

    @Override
    public SalesBillDetailDTO getSingleSalesInfo(int id) {
        return dbService.getDao(SalesBillDetailDAO.class).getSingleSaleInfoById(id);
    }

    @Override
    public int  addNewSalesInfo(SalesBillDetailDTO salesBillDetailDTO) {
       return  dbService.getDao(SalesBillDetailDAO.class).addNewSalesInfo(salesBillDetailDTO);
    }


    @Override
    public void deleteSalesById(int id) {
        dbService.getDao(SalesBillDetailDAO.class).deleteSalesById(id);
    }

    @Override
    public void editSalesInfo(SalesBillDetailDTO salesBillDetailDTO, int id) {
        dbService.getDao(SalesBillDetailDAO.class).editSalesById(salesBillDetailDTO, id);
    }
}
