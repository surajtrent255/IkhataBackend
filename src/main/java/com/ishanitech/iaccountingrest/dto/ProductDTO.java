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
public class ProductDTO {
       private int id;
       private String name;
       private double sellingPrice;
       private double costPrice;
       private Date createDate;
       private Date updateDate;
       private int userId;
       private int companyId;
       private int sellerId;
       private int categoryId;
       private String barcode;
       private double discount;
       private double tax;
       private boolean deleted;
}
