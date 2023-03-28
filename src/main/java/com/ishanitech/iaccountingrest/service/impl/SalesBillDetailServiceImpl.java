package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.SalesBillDAO;
import com.ishanitech.iaccountingrest.dao.SalesBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.SaleBillMasterDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDetailDTO;
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
    public SaleBillMasterDTO getSalesInfoByBillId(int billId, int companyId) {
        SaleBillMasterDTO saleBillMasterDTO = new SaleBillMasterDTO();

        List<SalesBillDetailDTO> salesBillDetailDTO = dbService.getDao(SalesBillDetailDAO.class).getSalesInfoByBillId(billId, companyId);
        saleBillMasterDTO.setSalesBillDetails(salesBillDetailDTO);
        saleBillMasterDTO.setSalesBillDTO(dbService.getDao(SalesBillDAO.class).getBillById(billId));
        return saleBillMasterDTO;
    }
}
