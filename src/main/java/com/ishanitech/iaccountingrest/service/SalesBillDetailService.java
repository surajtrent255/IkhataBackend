package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;

import java.util.List;

public interface SalesBillDetailService {
    List<SalesBillDetailDTO> getAllSalesInfo();

    SalesBillDetailDTO getSingleSalesInfo(int id);

    int addNewSalesInfo(SalesBillDetailDTO salesBillDetailDTO);

    void deleteSalesById(int id);

    void editSalesInfo(SalesBillDetailDTO salesBillDetailDTO, int id);
}
