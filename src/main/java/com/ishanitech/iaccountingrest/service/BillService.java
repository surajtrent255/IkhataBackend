package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.SalesBillDTO;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

public interface BillService {

        List<SalesBillDTO> getAllBills();

        SalesBillDTO getBillById(int id);

        void deleteBillById(int id);

        Integer printTheBill(int billId, int userId);

        List<SalesBillDTO> getAllBillsByCompId(int compId, int branchId);

        Object approveTheBillById(int billId);

        List<SalesBillDTO> getLimitedSalesBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems,
                        String searchBy, String searchWildCard, Integer compId, Integer branchId);

        Double todayTotalSalesBillAmount(String todayDate, int companyId, int branchId);

        Double ThisMonthTotalSalesBillAmount(String month, int companyId, int branchId);

        Double fiscalYearTotalSalesBillAmount(String fiscalYear, int companyId, int branchId);

        Double todayTotalSalesBillTaxAmount(String todayDate, int companyId, int branchId);

        Double ThisMonthTotalSalesBillTaxAmount(String month, int companyId, int branchId);

        Double fiscalYearTotalSalesBillTaxAmount(String fiscalYear, int companyId, int branchId);

        List<SalesBillDTO> getLimitedSalesBillsExcludingDraftByCompIdAndBranchId(String fiscalYear, Integer quarter,
                        Integer offset, Integer pageTotalItems,
                        String searchBy, String searchWildCard, Integer compId, Integer branchId);

        List<SalesBillDTO> getAllDebtors(Integer companyId, Integer branchId);

        List<SalesBillDTO> getAllDebtors(HttpServletRequest request);

        List<SalesBillDTO> getDebtorsBillList(HttpServletRequest request);

}