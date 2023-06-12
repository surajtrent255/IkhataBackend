package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitNoteDTO {

    private int id;
    private Long panNumber;

    private String receiverName;

    private String receiverAddress;

    private Long billNumber;

    private Date date;

    private String nepaliDate;

    private Double totalAmount;

    private Double totalTax;

    private int companyId;

    private int branchId;



}
