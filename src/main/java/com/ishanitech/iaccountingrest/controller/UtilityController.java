package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.TaxFileIrdDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BillService;
import com.ishanitech.iaccountingrest.service.UtilityService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utility")
@RequiredArgsConstructor
@Slf4j
public class UtilityController {

    private final UtilityService utilityService;
    private final BillService billService;

    @GetMapping("/summary")
    public ResponseDTO<TaxFileIrdDTO> taxFileUtilitySummary(@RequestParam("compId") Integer compId,
            @RequestParam("fiscal_year") String fiscalYear, @RequestParam("quarterStart") String qrtStart, 
            @RequestParam("quarterEnd") String qrtEnd) {
        try {
            return new ResponseDTO<>(utilityService.findTaxFileUtilitySummary(compId, fiscalYear,  qrtStart,  qrtEnd));
        } catch (Exception ex) {
            log.error("something went wrong while fetching utility summary" + ex.getMessage());
            throw new CustomSqlException("something went wrong.. ");
        }
    }

    @GetMapping("/salesBill/list")
    public ResponseDTO<List<SalesBillDTO>> getLimitedBillsByCompId(
            @RequestParam("fiscalYear") String fiscalYear,
            @RequestParam("quarter") Integer quarter,
            @RequestParam("offset") Integer offset,
            @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchWildCard") String searchWildCard,
            @RequestParam("compId") Integer compId,
            @RequestParam("branchId") Integer branchId) {
        try {
            return new ResponseDTO<List<SalesBillDTO>>(
                    billService.getLimitedSalesBillsExcludingDraftByCompIdAndBranchId(fiscalYear, quarter, offset,
                            pageTotalItems, searchBy, searchWildCard, compId, branchId));
        } catch (Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : ");
        }
    }

    @GetMapping("/purchaseBill/list/limited")
    public ResponseDTO<List<PurchaseBillDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset,
            @RequestParam("fiscalYear") String fiscalYear,
            @RequestParam("quarter") Integer quarter,
            @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId,
            @RequestParam("searchInput") String searchInput, @RequestParam("searchValue") String searchValue) {
        try {
            return new ResponseDTO<List<PurchaseBillDTO>>(utilityService.getLimitedPurchaseBillsForIrd(offset,
                    fiscalYear, quarter, pageTotalItems, compId, branchId, searchInput, searchValue));
        } catch (Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the purchase bill infos : ");
        }
    }

    @GetMapping("/summaryByMonth")
    public ResponseDTO<TaxFileIrdDTO> taxFileUtilitySummaryByMonth(@RequestParam("compId") Integer compId,
            @RequestParam("monthBegDate") String monthBegDate, 
            @RequestParam("monthEndDate") String monthEndDate,
            @RequestParam("fiscalYear") String fiscalYear) {
        try {
            return new ResponseDTO<TaxFileIrdDTO>(
                    utilityService.findTaxFileUtilitySummaryByMonth(compId, monthBegDate, monthEndDate, fiscalYear));
        } catch (Exception ex) {
            log.error("something went wrong while fetching utility summary" + ex.getMessage());
            throw new CustomSqlException("something went wrong.. ");
        }
    }

    @PostMapping("/sendEmail")
    public ResponseDTO<Integer> sendEmail(@RequestBody Map<?, ?> data, @RequestParam("type") String type){
        try{
            utilityService.sendEmail(data, type);
            return new ResponseDTO<>(1);
        } catch(Exception ex){
            log.error("someting went wroing while sending mail in utility ==> ", ex);
            throw new CustomSqlException("something went wrong !!!0");
        }
    }

}
