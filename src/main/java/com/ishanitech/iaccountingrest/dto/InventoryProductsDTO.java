package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryProductsDTO {
    private int productId;
    private String productName;
    private String categoryName;
    private String barcode;
    private int stockQty;
}
