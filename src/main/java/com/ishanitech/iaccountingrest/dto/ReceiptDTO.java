package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO {
    private int SN;

    private int companyId;

    private int partyId;

    private Double amount;

    private Date date;

    private String  nepaliDate;

    private int modeId;

    private Double tdsDeductedAmount;

    private Boolean postDateCheck;

    private int branchId;

    private Boolean status;
}
