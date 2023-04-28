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
public class BankWithdrawDTO {
    private  int company_id;
    private  int amount;
    private  int check_number ;
    private Date withdraw_date;
    private  String widhdraw_type;
}
