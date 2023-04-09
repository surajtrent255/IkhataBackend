package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailWithProdInfoDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillInvoiceDTO;
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
    public SalesBillDetailDTO getSingleSalesInfo(int id) {
        return dbService.getDao(SalesBillDetailDAO.class).getSingleSaleInfoById(id);
    }

    @Override
    public SalesBillInvoiceDTO getSalesInfoByBillId(int billId) {
        SalesBillInvoiceDTO salesBillInvoiceDTO = new SalesBillInvoiceDTO();
        List<SalesBillDetailWithProdInfoDTO> salesBillDetailWithProdInfoDTOS = dbService.getDao(SalesBillDetailDAO.class).getSalesInfoWithProdNameByBillId(billId);
        salesBillInvoiceDTO.setSalesBillDetailsWithProd(salesBillDetailWithProdInfoDTOS);
        salesBillInvoiceDTO.setSalesBillDTO(dbService.getDao(SalesBillDAO.class).getBillById(billId));
        return salesBillInvoiceDTO;
    }
}
