package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiscalYearInfo {

    private Integer id;
    private String fiscalYear;
    private LocalDateTime firstQuarter;
    private LocalDateTime secondQuarter;
    private LocalDateTime thirdQuarter;
    private LocalDateTime fourthQuarter;
    private boolean active;
}
