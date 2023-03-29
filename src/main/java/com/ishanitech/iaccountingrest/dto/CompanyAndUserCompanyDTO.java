package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAndUserCompanyDTO {
    private int companyId;

    private String name;

    private String description;

    private Long panNo;

    private int state;

    private String zone;

    private String district;

    private String munVdc;

    private int wardNo;

    private Long phone;

    private int userId;
}
