package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SplitProductDTO {
    private int id;
    private int productId;
    private String ProductName;
    private int qty;
    private int splitQty;
    private int totalQty;
    private String Unit;
    private int price;
    private  int updatedProductId;
    private String updatedProductName;
    private int companyId;
    private int branchId;
    private int tax;

}
