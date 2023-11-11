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
    private LocalDateTime firstQuarterStart;
    private LocalDateTime firstQuarterEnd;
    private LocalDateTime secondQuarterStart;
    private LocalDateTime secondQuarterEnd;
    private LocalDateTime thirdQuarterStart;
    private LocalDateTime thirdQuarterEnd;
    private boolean active;
}
