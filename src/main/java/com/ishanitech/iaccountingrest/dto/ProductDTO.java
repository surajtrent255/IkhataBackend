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
       private Date create_date;
       private Date updated_date;
       private int userId;
       private int companyId;
       private int sellerId;
       private int categoryId;
       private boolean deleted;
}
