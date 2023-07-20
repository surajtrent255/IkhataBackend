package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.OtherIncomeDTO;
import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.OtherIncomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/otherIncome")
@RequiredArgsConstructor
public class OtherIncomeController {
    private final OtherIncomeService otherIncomeService;

    @GetMapping
    public Mono<ResponseDTO<List<OtherIncomeDTO>>> getAllOtherIncomes(@RequestParam("compId") Integer companyId,
                                                                      @RequestParam("branchId") Integer branchId) {
        return Mono.fromCallable(() -> new ResponseDTO<List<OtherIncomeDTO>>(otherIncomeService.getAllOtherIncomes(companyId, branchId)))
                .onErrorResume(throwable -> {
                    log.error("something went wrong while fetching all other incomes => " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("something went wrong"));
                });
    }

    @GetMapping("/single")
    public Mono<ResponseDTO<OtherIncomeDTO>> getOtherIncomeById(@RequestParam("id") Integer id,
                                                                @RequestParam("compId") Integer companyId,
                                                                @RequestParam("branchId") Integer branchId) {
        return Mono.fromCallable(() -> new ResponseDTO<OtherIncomeDTO>(otherIncomeService.getOtherIncomeById(id, companyId, branchId)))
                .onErrorResume(throwable -> {
                    log.error("error fetching other income by id ==>  " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("something went wrong"));
                });
    }

    @PostMapping
    public Mono<ResponseDTO<Integer>> addNewOtherIncome(@RequestBody OtherIncomeDTO otherIncome) {
        return Mono.fromCallable(() -> new ResponseDTO<Integer>(otherIncomeService.insertNewOtherIncome(otherIncome)))
                .onErrorResume(throwable -> {
                    log.error("something went wrong while adding new other income " + throwable.getMessage());
                    return Mono.error(new CustomSqlException(("something went wrong")));
                });
    }

    @PutMapping("/{id}")
    public Mono<Void> updateGivenOtherIncome(@RequestBody OtherIncomeDTO otherIncomeDTO, @PathVariable("id") Integer id) {
        return Mono.defer(() -> {
            try {
                otherIncomeService.updateGivenOtherIncome(otherIncomeDTO);
                return Mono.empty();
            } catch (Exception ex) {
                log.error("updating the other income ==> " + ex.getMessage());
                return Mono.error(new CustomSqlException("something went wrong"));
            }
        });
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteGivenOtherIncome(@PathVariable("id") Integer id,
                                             @RequestParam("compId") Integer companyId,
                                             @RequestParam("branchId") Integer branchId) {
        return Mono.defer(() -> {
            try {
                otherIncomeService.deleteOtherIncomeById(id, companyId, branchId);
                return Mono.empty();
            } catch (Exception ex) {
                log.error("something went wrong while deleting given other income " + ex.getMessage());
                return Mono.error(new CustomSqlException("something went wrong"));
            }
        });
    }

    @GetMapping("/types")
    public Mono<ResponseDTO<List<OtherIncomeSourceDTO>>> getAllOtherIncomeTypes() {
        return Mono.fromCallable(() -> new ResponseDTO<List<OtherIncomeSourceDTO>>(otherIncomeService.getAllOtherIncomeTypes()))
                .onErrorResume(throwable -> {
                    log.error("error fetching other income types ===> " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("something went wrong"));
                });
    }
}