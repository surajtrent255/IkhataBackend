package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.EmployeeDAO;
import com.ishanitech.iaccountingrest.dto.EmployeeDTO;
import com.ishanitech.iaccountingrest.dto.EmployeeTypeDTO;
import com.ishanitech.iaccountingrest.dto.PaginationTypeClass;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.EmailService;
import com.ishanitech.iaccountingrest.service.EmployeeService;
import com.ishanitech.iaccountingrest.utils.CustomQueryCreator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl  implements EmployeeService {

    private final DbService dbService;
    @Override
    public List<EmployeeDTO> getAllEmployees( HttpServletRequest request) {


        String caseQuery = CustomQueryCreator.generateQueryWithCase(request, PaginationTypeClass.EMPLOYEE, dbService);
        EmployeeDAO employeeDAO = dbService.getDao(EmployeeDAO.class);
        List<EmployeeDTO> employeeDTOS ;
        employeeDTOS = employeeDAO.getAllEmployee(caseQuery);
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
        employee.setEntryDate(new Date());
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

    @Override
    public List<EmployeeTypeDTO> getAllEmployeeType() {
        EmployeeDAO employeeDAO = dbService.getDao(EmployeeDAO.class);
        List<EmployeeTypeDTO> employeeTypeDTOS;
        employeeTypeDTOS = employeeDAO.getAllEmployeeTypes();
        return employeeTypeDTOS;

    }

}
