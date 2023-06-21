package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.*;
import com.ishanitech.iaccountingrest.dto.*;
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
        CompanyDTO customer = dbService.getDao(CompanyDAO.class).getCompanyByCompanyId(salesBillInvoiceDTO.getSalesBillDTO().getCustomerId());
        if(customer != null){
            if(customer.getState() != 8){
                salesBillInvoiceDTO.setCustomerAddress(customer.getMunVdc() +" "+ customer.getDistrict());
            }
        }
        int compId = salesBillInvoiceDTO.getSalesBillDTO().getCompanyId();
        CompanyDTO sellerCompany =  dbService.getDao(CompanyDAO.class).getCompanyByCompanyId(compId);
        salesBillInvoiceDTO.setSellerCompany(sellerCompany);
        SalesReceiptDTO salesReceiptDTO = dbService.getDao(BillNoGeneratorDAO.class).getSalesReceipt(salesBillInvoiceDTO.getSalesBillDTO().getBillNo());
        salesBillInvoiceDTO.setSalesReceipt(salesReceiptDTO);
        return salesBillInvoiceDTO;
    }

    @Override
    public SalesBillInvoiceDTO getSalesInfoByBillNo(String billNo) {
        SalesBillInvoiceDTO salesBillInvoiceDTO = new SalesBillInvoiceDTO();
        List<SalesBillDetailWithProdInfoDTO> salesBillDetailWithProdInfoDTOS = dbService.getDao(SalesBillDetailDAO.class).getSalesInfoWithProdNameByBillNo(billNo);
        salesBillInvoiceDTO.setSalesBillDetailsWithProd(salesBillDetailWithProdInfoDTOS);
        salesBillInvoiceDTO.setSalesBillDTO(dbService.getDao(SalesBillDAO.class).getBillByBillNo(billNo));
        CompanyDTO customer = dbService.getDao(CompanyDAO.class).getCompanyByCompanyId(salesBillInvoiceDTO.getSalesBillDTO().getCustomerId());
        if(customer != null){
            salesBillInvoiceDTO.setCustomerAddress(customer.getMunVdc() +" "+ customer.getDistrict());
        }
        int compId = salesBillInvoiceDTO.getSalesBillDTO().getCompanyId();
        CompanyDTO sellerCompany =  dbService.getDao(CompanyDAO.class).getCompanyByCompanyId(compId);
        salesBillInvoiceDTO.setSellerCompany(sellerCompany);
        SalesReceiptDTO salesReceiptDTO = dbService.getDao(BillNoGeneratorDAO.class).getSalesReceipt(salesBillInvoiceDTO.getSalesBillDTO().getBillNo());
        salesBillInvoiceDTO.setSalesReceipt(salesReceiptDTO);
        return salesBillInvoiceDTO;
    }

    @Override
    public List<SalesBillDetailDTO> getSaleBillDetailByBillId(int billId) {
        List<SalesBillDetailDTO> salesBillDetailDTOS = dbService.getDao(SalesBillDetailDAO.class).getSalesInfoByBillId(billId);

        return salesBillDetailDTOS;
    }
}
