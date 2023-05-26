package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.ReceiptDAO;
import com.ishanitech.iaccountingrest.dto.ReceiptDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private DbService dbService;
    @Override
    public List<ReceiptDTO> getReceipts(int companyId) {
        ReceiptDAO receiptDAO = dbService.getDao(ReceiptDAO.class);
        return receiptDAO.getAllReceipts(companyId);
    }

    @Override
    public Integer addReceipts(ReceiptDTO receiptDTO) {
        ReceiptDAO receiptDAO = dbService.getDao(ReceiptDAO.class);
        int success = receiptDAO.addReceipt(receiptDTO);
        return success;
    }

    @Override
    public void deleteReceipts(int SN) {
        ReceiptDAO receiptDAO = dbService.getDao(ReceiptDAO.class);
        receiptDAO.deleteReceipt(SN);
    }

    @Override
    public List<ReceiptDTO> getLimitedReceiptsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId) {
        List<ReceiptDTO> receipts;
        receipts = dbService.getDao(ReceiptDAO.class).getLimitedReceiptsByCompanyAndBranchId(offset, pageTotalItems, compId, branchId);
        return receipts;
    }
}
