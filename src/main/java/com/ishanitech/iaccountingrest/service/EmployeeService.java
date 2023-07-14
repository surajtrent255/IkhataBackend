package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees(Integer companyId, Integer branchId);

    EmployeeDTO getEmployeeById(Integer id, Integer companyId, Integer branchId);

    Integer insertNewEmployee(EmployeeDTO employee);

    void updateGivenEmployee(EmployeeDTO employeeDTO);

    void deleteEmployeeById(Integer id, Integer companyId, Integer branchId);
}
