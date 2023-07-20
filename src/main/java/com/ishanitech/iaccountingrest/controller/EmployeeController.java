package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.EmployeeDTO;
import com.ishanitech.iaccountingrest.dto.EmployeeTypeDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public Mono<ResponseDTO<List<EmployeeDTO>>> getAllEmployees( @RequestParam("compId") Integer companyId,
                                                                @RequestParam("branchId") Integer branchId){return Mono.fromCallable(()-> new ResponseDTO<List<EmployeeDTO>>(employeeService.getAllEmployees(companyId, branchId)))
                .onErrorResume(throwable -> {
                    log.error("something went wrong  while fetching all employees => "+throwable.getMessage());
                    return Mono.error(new CustomSqlException("some thing went wrong "));
                });

    }

    @GetMapping("/single")
    public Mono<ResponseDTO<EmployeeDTO>> getEmployeeById(@RequestParam("id") Integer id, @RequestParam("compId") Integer companyId, @RequestParam("branchId") Integer branchId){
        return Mono.fromCallable(()-> new ResponseDTO<EmployeeDTO>(employeeService.getEmployeeById(id, companyId, branchId))).onErrorResume(throwable -> {
            log.error("error fetching employee by id ==>  " + throwable.getMessage());
            return Mono.error(new CustomSqlException(" something went wrong "));
        });
    }

    @PostMapping
    public Mono<ResponseDTO<Integer>> addNewEmployee(@RequestBody EmployeeDTO employee){
        return Mono.fromCallable(()-> new ResponseDTO<Integer>(employeeService.insertNewEmployee(employee))).onErrorResume(
                throwable -> {
                    log.error("something went wrong while adding new Employee " + throwable.getMessage());
                    return Mono.error(new CustomSqlException(("Some thing went wrong")));
                }
        );
    }

    @PutMapping("/{id}")
    public Mono<Void> updateGivenEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable("id") Integer id){
       return Mono.defer(() ->{
           try{
                employeeService.updateGivenEmployee(employeeDTO);
                return Mono.empty();
           } catch (Exception ex){
               log.error("updating the employee ==> " + ex.getMessage());
               return Mono.error(new CustomSqlException("Something went wrong"));
           }
       });
    }

    @DeleteMapping("/{id}")
    public void deleteGivenEmployee(@PathVariable("id") Integer id, @RequestParam("compId") Integer companyId,
                                    @RequestParam("branchId") Integer branchId){
        Mono.fromCallable(()-> {
            employeeService.deleteEmployeeById(id, companyId, branchId);
            return  Mono.empty();
        }).onErrorResume(throwable -> {
            log.error("something went wrong while deleting given employee " + throwable.getMessage());
            return Mono.error(new CustomSqlException("something went wrong"));
        }).subscribe();
    }


    @GetMapping("/types")
    public Mono<ResponseDTO<List<EmployeeTypeDTO>>> getALlEmployeeTypes(){
        return Mono.fromCallable(()-> new ResponseDTO<List<EmployeeTypeDTO>>(employeeService.getAllEmployeeType()))
                .onErrorResume(throwable -> {
                   log.error("error fetching employee type ===> "+ throwable.getMessage());
                   return Mono.error(new CustomSqlException("something went wrong"));
                });

    }
}
