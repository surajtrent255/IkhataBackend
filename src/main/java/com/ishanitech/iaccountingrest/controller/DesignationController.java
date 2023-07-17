package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.DesignationDTO;
import com.ishanitech.iaccountingrest.dto.EmployeeDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.DesignationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employee/designation")
@RequiredArgsConstructor
public class DesignationController {

    private final DesignationService designationService;
    @GetMapping
    public Mono<ResponseDTO<List<DesignationDTO>>> getAllDesignation(
            @RequestParam("compId") Integer companyId,
            @RequestParam("branchId") Integer branchId
    ){
        return Mono.fromCallable(()-> new ResponseDTO<List<DesignationDTO>>(designationService.getAllDesignationForAStation(companyId, branchId))).onErrorResume(
                throwable -> {
                    log.error("error fetching designation ==> " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("something went wrong "));
                }
        );
    }

    @PostMapping
    public Mono<ResponseDTO<Integer>> addNewDesignation(@RequestBody DesignationDTO designation){
        return Mono.fromCallable(()-> new ResponseDTO<Integer>(designationService.addNewDesignation(designation))).onErrorResume(
                throwable -> {
                    log.error("error adding new designation ===> " + throwable.getMessage());
                    return Mono.error(new CustomSqlException("something went wrong"));
                }
        );
    }


}
