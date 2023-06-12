package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SalesReceiptDTO {
    private Integer id;
    private Integer receiptNo;
    private LocalDate receiptDate;
    private Double receiptAmount;
    private String billNo;
    private boolean hasAbbr;
    private Integer companyId;
    private Integer branchId;

}
