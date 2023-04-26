package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankWithdrawDTO {

    private int withdrawId;
    private  int companyId;
    private int branchId;
    private  float withdrawAmount;
    private  String withdrawType;
    private  Date withdrawDate;
    private String chequeNumber;

}
