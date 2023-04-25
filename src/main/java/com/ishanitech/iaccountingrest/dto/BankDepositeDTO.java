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
    private int bank_id ;
    private  int company_id;
    private  int amount;
    private  int check_number ;
    private Date submitt_date;
    private  String deposite_type ;

}
