package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.ReceiptDTO;

import java.util.List;

public interface ReceiptService {
    List<ReceiptDTO> getReceipts(int companyId);

    Integer addReceipts(ReceiptDTO receiptDTO);

    void deleteReceipts(int SN);

    List<ReceiptDTO> getLimitedReceiptsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
