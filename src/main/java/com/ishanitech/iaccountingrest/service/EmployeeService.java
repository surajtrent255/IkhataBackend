package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.EmployeeDTO;
import com.ishanitech.iaccountingrest.dto.EmployeeTypeDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAllEmployees(HttpServletRequest request);

    EmployeeDTO getEmployeeById(Integer id, Integer companyId, Integer branchId);

    Integer insertNewEmployee(EmployeeDTO employee);

    void updateGivenEmployee(EmployeeDTO employeeDTO);

    void deleteEmployeeById(Integer id, Integer companyId, Integer branchId);

    List<EmployeeTypeDTO> getAllEmployeeType();
}
