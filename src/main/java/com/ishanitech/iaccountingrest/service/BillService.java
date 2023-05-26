package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.SalesBillDTO;

import java.util.List;

public interface BillService {

        List<SalesBillDTO> getAllBills();

        SalesBillDTO getBillById(int id);

        void deleteBillById(int id);

        Integer printTheBill(int billId, int userId);

        List<SalesBillDTO> getAllBillsByCompId(int compId, int branchId);

        Object approveTheBillById(int billId);

        List<SalesBillDTO> getLimitedSalesBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, int compId, int branchId);
}