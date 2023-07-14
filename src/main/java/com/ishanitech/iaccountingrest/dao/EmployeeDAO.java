package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.EmployeeDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface EmployeeDAO {

    @SqlQuery("""
            select * from employee where company_id = :companyId and branch_id = :branchId and deleted = false;
            """)
    @RegisterBeanMapper(EmployeeDTO.class)
    List<EmployeeDTO> getAllEmployee(int companyId, int branchId);

    @SqlQuery("""
            select * from employee where company_id = :companyId and branch_id = :branchId and sn = :id and deleted = false;
            """)
    @RegisterBeanMapper(EmployeeDTO.class)
    EmployeeDTO getSinglelEmployee(int id, int companyId, int branchId);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO employee (name, designation, panNo, salary, employee_type, married, company_id, branch_id, join_date, entry_date)"
            + " VALUES (:name, :designation, :panNo, :salary, :employeeType, :married, :companyId, :branchId, :joinDate, :entryDate)")
    int addNewAnotherEmployee(@BindBean EmployeeDTO employee);

    @SqlUpdate("UPDATE employee SET name = :name, designation = :designation, salary = :salary, employee_type = :employeeType, married = :married, "
            + "company_id = :companyId, branch_id = :branchId, join_date = :joinDate WHERE sn = :sn")
    int updateParticularEmployee(@BindBean EmployeeDTO employeeDTO, int sn);

    @SqlUpdate("UPDATE employee SET deleted = true WHERE sn = :id")
    int deleteParticularEmployee(int id, int companyId, int branchId);

}
