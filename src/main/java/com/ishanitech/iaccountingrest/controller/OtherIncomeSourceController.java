package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.OtherIncomeSourceDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.OtherIncomeSourceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/otherIncomeSource")
@RequiredArgsConstructor
public class OtherIncomeSourceController {

    private final OtherIncomeSourceService otherIncomeSourceService;

    @GetMapping
    public Mono<ResponseDTO<List<OtherIncomeSourceDTO>>> getAllOtherIncomeSources(
            @RequestParam("compId") Integer companyId,
            @RequestParam("branchId") Integer branchId
    ){
        return Mono.fromCallable(() ->
                        new ResponseDTO<>(otherIncomeSourceService.getAllOtherIncomeSources(companyId, branchId)))
                .onErrorResume(throwable -> {
                    log.error("error fetching other income sources => " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("something went wrong"));
                });
    }

    @PostMapping
    public Mono<ResponseDTO<Integer>> addNewOtherIncomeSource(@RequestBody OtherIncomeSourceDTO otherIncomeSource){
        return Mono.fromCallable(() ->
                        new ResponseDTO<>(otherIncomeSourceService.addNewOtherIncomeSource(otherIncomeSource)))
                .onErrorResume(throwable -> {
                    log.error("error adding new other income source => " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("something went wrong"));
                });
    }
}
