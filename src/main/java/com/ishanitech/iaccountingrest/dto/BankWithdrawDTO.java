package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankWithdrawDTO {

    private int withdrawId;
    private  int companyId;
    private int branchId;
    private  int withdrawAmount;
    private  String withdrawType;
    private  Date withdrawDate;
    private String chequeNumber;

}
