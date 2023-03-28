package com.ishanitech.iaccountingrest.dto;

import lombok.*;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private int id;
    private int productId;
    private int qty;
    private int companyId;
    private Date createDate;
    private Date updateDate;

}
