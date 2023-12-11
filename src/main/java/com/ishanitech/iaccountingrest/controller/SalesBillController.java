package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BillService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salesBill")
@Slf4j
@RequiredArgsConstructor
public class SalesBillController {

    private final BillService billService;

    @GetMapping
//    public ResponseDTO<List<SalesBillDTO>> getAllBills(){
//        try{
//            return new ResponseDTO<List<SalesBillDTO>>(billService.getAllBills());
//        } catch(Exception e) {
//            log.error("Error occured accessing the bill infos : " + e.getMessage());
//            throw new CustomSqlException("Error occured accessing the bill infos : " );
//        }
//    }

    public Single<ResponseDTO<List<SalesBillDTO>>> getAllBills() {
        return Single.fromCallable(() -> new ResponseDTO<>(billService.getAllBills()))
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext(throwable -> {
                    log.error("Error occurred accessing the bill info: " + throwable.getMessage());
                    return Single.error(new CustomSqlException("Error occurred accessing the bill info."));
                });
    }

    @GetMapping("/company")
    public ResponseDTO<List<SalesBillDTO>> getAllBillsByCompId(@RequestParam("compId") int compId, @RequestParam("branchId") int branchId){
        try{
            return new ResponseDTO<List<SalesBillDTO>>(billService.getAllBillsByCompId(compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
    }

    @GetMapping("/company/limited")
    public ResponseDTO<List<SalesBillDTO>> getLimitedBillsByCompId(
            @RequestParam("offset") Integer offset,
            @RequestParam("pageTotalItems") Integer pageTotalItems,
            @RequestParam("searchBy") String searchBy,
            @RequestParam("searchWildCard") String searchWildCard,
            @RequestParam("compId") Integer compId,
            @RequestParam("branchId") Integer branchId){
        try{
            return new ResponseDTO<List<SalesBillDTO>>(billService.getLimitedSalesBillsByCompIdAndBranchId(offset, pageTotalItems, searchBy, searchWildCard, compId, branchId));
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
    }
    
    @GetMapping("/{id}")
    public ResponseDTO<SalesBillDTO> getBillById(@PathVariable int id){
        try{
            return new ResponseDTO<SalesBillDTO>(billService.getBillById(id));
        } catch(Exception e) {
            log.error("Error occured accessing the bill info : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill info : " );
        }
    }


    @PostMapping("/approve/{id}")
    public ResponseDTO<?> approveTheDraft(@PathVariable("id") int billId){
        try{
            return new ResponseDTO<>(billService.approveTheBillById(billId));
        } catch(Exception ex){
            log.error("Error occured while approving the bill "+ ex.getMessage());
            throw new CustomSqlException("something went wrong while approving the bill ");
        }

    }


    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable("id") int id){
        try{
            billService.deleteBillById(id);
        } catch(Exception ex){
            log.error("Error occured deleting the bill info " + ex.getMessage());
            throw new CustomSqlException("Error occured deleting the bill info : " + ex.getMessage());
        }
    }

    @PostMapping("/print/{billId}")
    public ResponseDTO<Integer> printTheBill(@PathVariable int billId, @RequestBody Map<String, Integer> printMap){
        try{
            return new ResponseDTO<Integer>(billService.printTheBill(billId, printMap.get("printerId")));
        } catch(Exception ex){
            log.error("error occured while updating printing info");
            throw new CustomSqlException("Something went wrong while updating printing info");
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
            return new ResponseDTO<>(billService.todayTotalSalesBillAmount(todayDate,companyId,branchId));
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
            return new ResponseDTO<>(billService.ThisMonthTotalSalesBillAmount(month,companyId,branchId));
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
            return new ResponseDTO<>(billService.fiscalYearTotalSalesBillAmount(fiscalYear,companyId,branchId));
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
            return new ResponseDTO<>(billService.todayTotalSalesBillTaxAmount(todayDate,companyId,branchId));
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
            return new ResponseDTO<>(billService.ThisMonthTotalSalesBillTaxAmount(month,companyId,branchId));
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
            return new ResponseDTO<>(billService.fiscalYearTotalSalesBillTaxAmount(fiscalYear,companyId,branchId));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/debtors")
    public ResponseDTO<List<SalesBillDTO>> getAllDebtors(
        // @RequestParam("companyId") Integer companyId,
        // @RequestParam("branchId") Integer branchId
        HttpServletRequest request
    ){
        try{
            return new ResponseDTO<>(billService.getAllDebtors(request));
        }
         catch(Exception ex){
            log.error(ex.getMessage());
            throw new CustomSqlException(ex.getMessage());
         }
         
    }

    @GetMapping("/debtors/billList/{debtorPan}")
    public ResponseDTO<List<SalesBillDTO>> getAllBill( HttpServletRequest request){
    //     @PathVariable("debtorPan") Long debtorPan,
    // @RequestParam("companyId") Integer companyId, @RequestParam("branchId") Integer branchId
        try{
            return new ResponseDTO<List<SalesBillDTO>>(billService.getDebtorsBillList(request));
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new CustomSqlException(ex.getMessage());
        }

    }

}
