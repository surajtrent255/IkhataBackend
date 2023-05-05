package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MergeProductDTO {
        private Integer id;
        private Integer productId;
        private String productName;
        private Integer splitProductId;
        private String splitProductName;
        private Integer price;
        private Integer qty;
        private Integer mergeQty;
        private String unit;
        private Integer tax;
        private Integer companyId;
        private Integer branchId;




}
