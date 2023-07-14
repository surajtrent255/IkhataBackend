package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.EmployeeDAO;
import com.ishanitech.iaccountingrest.dto.EmployeeDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.EmailService;
import com.ishanitech.iaccountingrest.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl  implements EmployeeService {

    private final DbService dbService;
    @Override
    public List<EmployeeDTO> getAllEmployees(Integer companyId, Integer branchId) {
        EmployeeDAO employeeDAO = dbService.getDao(EmployeeDAO.class);
        List<EmployeeDTO> employeeDTOS ;
        employeeDTOS = employeeDAO.getAllEmployee(companyId, branchId);
        return employeeDTOS;
    }

    @Override
    public EmployeeDTO getEmployeeById(Integer id, Integer companyId, Integer branchId) {
        EmployeeDAO employeeDAO = dbService.getDao(EmployeeDAO.class);
        EmployeeDTO employee ;
        employee= employeeDAO.getSinglelEmployee(id, companyId, branchId);
        return employee;
    }

    @Override
    public Integer insertNewEmployee(EmployeeDTO employee) {
        EmployeeDAO employeeDAO = dbService.getDao(EmployeeDAO.class);
        int id;
        id = employeeDAO.addNewAnotherEmployee(employee);
        return id;
    }

    @Override
    public void updateGivenEmployee(EmployeeDTO employee) {
        EmployeeDAO employeeDAO = dbService.getDao(EmployeeDAO.class);
        int id;
        id = employeeDAO.updateParticularEmployee(employee, employee.getSn());
    }

    @Override
    public void deleteEmployeeById(Integer id, Integer companyId, Integer branchId) {
        EmployeeDAO employeeDAO = dbService.getDao(EmployeeDAO.class);
        employeeDAO.deleteParticularEmployee(id, companyId, branchId);
    }
}
