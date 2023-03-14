package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesDTO {
    private int id;
    private int product_id;
    private int quantity;
    private Date date;
    private double discount;
    private double Rate;
    private int billId;
    private int companyId;
    private boolean deleted;
}
