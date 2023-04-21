package com.ishanitech.iaccountingrest.dto;

import com.ishanitech.iaccountingrest.service.DbService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {


    private int SN;
    private int companyId;

    private int partyId;

    private Double amount;

    private int paymentModeId;

    private Double tdsDeductedAmount;

    private boolean postDateCheck;

    private int branchId;

    private Date date;

    private boolean status;

    private PaymentModeDTO paymentModeDTO;



}
