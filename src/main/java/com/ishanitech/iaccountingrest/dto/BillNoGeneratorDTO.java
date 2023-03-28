package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillNoGeneratorDTO {
    private int id;
    private String fiscalYear;
    private int billNo;
    private boolean active;
}
