package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitNoteDetailsDTO {

    private int SN;

    private  int productId;

    private String productName;

    private String debitReason;

    private Double debitAmount;

    private Double debitTaxAmount;

}
