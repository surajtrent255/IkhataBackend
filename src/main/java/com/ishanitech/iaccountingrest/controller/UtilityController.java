package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.TaxFileIrdDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BillService;
import com.ishanitech.iaccountingrest.service.UtilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestParam("fiscal_year") String fiscalYear, @RequestParam("quarter") Integer quarter) {
        try {
            return new ResponseDTO<>(utilityService.findTaxFileUtilitySummary(compId, fiscalYear, quarter));
        } catch (Exception ex) {
            log.error("something went wrong while fetching utility summary" + ex.getMessage());
            throw new CustomSqlException("something went wrong.. ");
        }
    }

    @GetMapping("/salesBill/list")
    public ResponseDTO<List<SalesBillDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset,
            @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchWildCard") String searchWildCard,
            @RequestParam("compId") Integer compId,
            @RequestParam("branchId") Integer branchId) {
        try {
            return new ResponseDTO<List<SalesBillDTO>>(billService.getLimitedSalesBillsExcludingDraftByCompIdAndBranchId(offset,
                    pageTotalItems, searchBy, searchWildCard, compId, branchId));
        } catch (Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : ");
        }
    }

}
