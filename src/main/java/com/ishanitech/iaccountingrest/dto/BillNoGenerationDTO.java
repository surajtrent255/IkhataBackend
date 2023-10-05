package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillNoGenerationDTO {
    private int id;
    private int billNo;
    private  boolean active;
    private int companyId;
    private int branchId;
    private String fiscalYear;
    private boolean hasAbbr;
}
