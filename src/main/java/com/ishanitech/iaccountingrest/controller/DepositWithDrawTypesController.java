package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.DepositWithdrawTypesDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DepositWithDrawTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/depositWithdrawTypes")
@RequiredArgsConstructor
public class DepositWithDrawTypesController {

    private final DepositWithDrawTypeService depositWithDrawTypeService;

    @GetMapping
    public Mono<ResponseDTO<List<DepositWithdrawTypesDTO>>> getAllDepositWithDrawTypes() {
        return Mono.fromCallable(() -> new ResponseDTO<>(depositWithDrawTypeService.getAllTypesOfDepositWithdraw()))
                .onErrorResume(throwable -> {
                    log.error("Error fetching depositWithDrawTypes: " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("Something went wrong while fetching depositWithDrawTypes"));
                });
    }
}
