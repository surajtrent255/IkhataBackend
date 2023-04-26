package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDepositeDTO {
    private int depositId;
    private int bankId ;
    private  int companyId;
    private  int branchId;
    private  int depositAmount;
    private  String depositType;
    private Date submiteDate;
    private  Long chequeNumber ;

}
