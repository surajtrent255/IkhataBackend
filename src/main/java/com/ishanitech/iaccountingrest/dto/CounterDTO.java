package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CounterDTO {
    private int id;
    private String name;

    private int counterId;

    private int userId;
    private int companyId;

    private int branchId;

    private Date date;

    private boolean status;

}
