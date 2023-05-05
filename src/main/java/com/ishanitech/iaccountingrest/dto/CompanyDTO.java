package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data ;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private int companyId;

    private String name;

    private String email;

    private String description;

    private Long panNo;

    private int state;
    
    private String district;

    private String munVdc;

    private int wardNo;
    private boolean customer;

    private Long phone;


}
