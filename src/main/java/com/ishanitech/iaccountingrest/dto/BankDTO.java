package com.ishanitech.iaccountingrest.dto;

import com.ishanitech.iaccountingrest.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    private int Id;
    private  int companyId;
    private  int branchId;
    private  String bankName;
    private int accountNumber;
    private  int initialAmount;
    private Date creationDate;
    private  String accountType;
}
