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
       private Integer id;
       private String name;
       private String description;
       private Double sellingPrice;
       private Double costPrice;
       private Date createDate;
       private Date updateDate;
       private Integer userId;
       private Integer companyId;
       private Integer branchId;
       private Integer sellerId;
       private Integer categoryId;
       private String barcode;
       private Double discount;
       private Double tax;
       private String unit;
       private  Integer qtyPerUnit;
       private boolean deleted;
}
