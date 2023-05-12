package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditNoteDTO {
    private int id;

    private Long panNumber;

    private String customerName;

    private String customerAddress;

    private String billNumber;

    private Date date;

    private Double totalAmount;

    private Double totalTax;

    private int companyId;

    private int branchId;

}
