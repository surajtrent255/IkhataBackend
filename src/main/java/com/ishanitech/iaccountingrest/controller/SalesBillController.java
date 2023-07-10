package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.BillService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salesBill")
@Slf4j
@RequiredArgsConstructor
public class SalesBillController {

    private final BillService billService;

    @GetMapping
    public ResponseDTO<List<SalesBillDTO>> getAllBills(){
        try{
            return new ResponseDTO<List<SalesBillDTO>>(billService.getAllBills());
        } catch(Exception e) {
            log.error("Error occured accessing the bill infos : " + e.getMessage());
            throw new CustomSqlException("Error occured accessing the bill infos : " );
        }
    }

//    @GetMapping
//    public Single<ResponseDTO<Flux<SalesBillDTO>>> getAllBills() {
//        return Single.fromCallable(() -> billService.getAllBills())
//                .map(bills -> new ResponseDTO<>(bills))
//                .subscribeOn(Schedulers.io())
//                .onErrorResumeNext(throwable -> {
//                    log.error("Error occurred accessing the bill info: " + throwable.getMessage());
//                    return Single.error(new CustomSqlException("Error occurred accessing the bill info."));
//                });
//    }

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

}
