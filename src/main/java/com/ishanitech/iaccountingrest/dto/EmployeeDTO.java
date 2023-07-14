package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private Integer sn;
    private String name;
    private Integer designation;
    private Long panNo;
    private Double salary;
    private Integer employeeType;
    private boolean married;
    private Integer companyId;
    private Integer branchId;
    private Date joinDate;
    private Date entryDate;
    private boolean deleted;
}