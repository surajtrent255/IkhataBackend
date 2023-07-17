package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseReportDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.PurchaseBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchaseBill")
@Slf4j
@RequiredArgsConstructor
public class PurchaseBillController {
    private final PurchaseBillService purchaseBillService;

    @GetMapping
    public ResponseDTO<List<PurchaseBillDTO>> getAllPurchaseBill(){
        try{
            return new ResponseDTO<List<PurchaseBillDTO>>(purchaseBillService.getAllPurchaseBills());
        } catch(Exception e){
            log.error("error occured while fetching purchase bills : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching purchase bills");
        }
    }

    @GetMapping("/company")
    public ResponseDTO<List<PurchaseBillDTO>> getAllPurchaseBillByCompanyIdAndBranchId(@RequestParam("compId") int compId, @RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<List<PurchaseBillDTO>>(purchaseBillService.getAllPurchaseBillsByCompanyId(compId, branchId));
        } catch(Exception e){
            log.error("error occured while fetching purchase bills : " + e.getMessage());
            throw new CustomSqlException("Error occured while fetching purchase bills");
        }
    }

    @GetMapping("/company/limited")
    public ResponseDTO<List<PurchaseBillDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset, @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("compId") Integer compId, @RequestParam("branchId") Integer branchId
            ,@RequestParam("searchInput") String searchInput, @RequestParam("searchValue") String searchValue)   {
        try{
            return new ResponseDTO<List<PurchaseBillDTO>>(purchaseBillService.getLimitedPurchaseBillsByCompIdAndBranchId(offset, pageTotalItems, compId, branchId,searchInput,searchValue));
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the purchase bill infos : " );
        }
    }

    /*
    controller for total Amount (today , this month, this year)
    @param date
     */

    @GetMapping("/date/today")
    public ResponseDTO<Double> getTodayTotalSalesBillAmount(@RequestParam("todayDate")String todayDate,
                                                            @RequestParam("companyId") int companyId,
                                                            @RequestParam("branchId") int branchId){
        try {
            return new ResponseDTO<>(purchaseBillService.todayTotalPurchaseBillAmount(todayDate,companyId,branchId));
        }
        catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/date/month")
    public ResponseDTO<Double> getThisMonthTotalSalesBillAmount(@RequestParam("month")String month,
                                                                @RequestParam("companyId") int companyId,
                                                                @RequestParam("branchId") int branchId){
        try {
            return new ResponseDTO<>(purchaseBillService.ThisMonthTotalPurchaseBillAmount(month,companyId,branchId));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/date/fiscalYear")
    public ResponseDTO<Double> getThisFiscalYearTotalSalesBillAmount(@RequestParam("fiscalYear")String fiscalYear,
                                                                     @RequestParam("companyId") int companyId,
                                                                     @RequestParam("branchId") int branchId){
        try {
            return new ResponseDTO<>(purchaseBillService.fiscalYearTotalPurchaseBillAmount(fiscalYear,companyId,branchId));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/date/tax/today")
    public ResponseDTO<Double> getTodayTotalSalesBillTaxAmount(@RequestParam("todayDate")String todayDate,
                                                            @RequestParam("companyId") int companyId,
                                                            @RequestParam("branchId") int branchId){
        try {
            return new ResponseDTO<>(purchaseBillService.todayTotalPurchaseBillTaxAmount(todayDate,companyId,branchId));
        }
        catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/date/tax/month")
    public ResponseDTO<Double> getThisMonthTotalSalesBillTaxAmount(@RequestParam("month")String month,
                                                                @RequestParam("companyId") int companyId,
                                                                @RequestParam("branchId") int branchId){
        try {
            return new ResponseDTO<>(purchaseBillService.ThisMonthTotalPurchaseBillTaxAmount(month,companyId,branchId));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/date/tax/fiscalYear")
    public ResponseDTO<Double> getThisFiscalYearTotalSalesBillTaxAmount(@RequestParam("fiscalYear")String fiscalYear,
                                                                     @RequestParam("companyId") int companyId,
                                                                     @RequestParam("branchId") int branchId){
        try {
            return new ResponseDTO<>(purchaseBillService.fiscalYearTotalPurchaseBillTaxAmount(fiscalYear,companyId,branchId));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseDTO<PurchaseBillDTO> getSinglePurchasBillInfo(@PathVariable("id") Integer id){
        try{
            return new ResponseDTO<PurchaseBillDTO>(purchaseBillService.getSinglePurchasBillInfo(id));
        } catch(Exception e){
            log.error("error occured while fetching purchas bill with id " + id +" "+ e.getMessage());
            throw new CustomSqlException("error occured while fetching purchase bill with id " + id +" ");
        }
    }

    @GetMapping("/report/{id}")
    public ResponseDTO<PurchaseReportDTO> getPurchaseBillInfoForReport(@PathVariable("id") Integer id,
                                                                       @RequestParam("compId") int compId,
                                                                       @RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<PurchaseReportDTO>(purchaseBillService.getPurchaseBillInfoForReport(id, compId, branchId));
        } catch(Exception ex){
            log.error(" error occured while fetching purchase bill info for report with id "+id +" "+ ex.getMessage());
            throw new CustomSqlException("error occured while fetching purchase bill infor for report");
        }
    }

    @DeleteMapping("/{id}")
    public void deletePurchaseBill(@PathVariable int id){
        try{
            purchaseBillService.deletePurchaseBill(id);
        } catch(Exception e){
            log.error("error occured during purchaseBill deletion with id " + id + " "+ e.getMessage());
            throw new CustomSqlException("error occured while deletig the purchaseBill");
        }
    }

}
