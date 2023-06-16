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
public class PurchaseBillDTO {

    private int userId;
    private int sellerId;
    private int companyId;
    private int branchId;
    private String fiscalYear ;
    private int purchaseBillNo ;
    private boolean billPrinted;
    private boolean billActive;
    private boolean realtime;
    private String sellerName ;
    private String sellerPan ;

    private String sellerAddress;
    private Date billDate  ;
    private String transactionalDate;
    private String transactionalDateNepali;
    private double amount ;
    private double discount ;
    private double taxableAmount ;
    private double taxAmount ;
    private double  totalAmount ;
    private boolean syncWithIrd;
    private String printedTime ;
    private String enteredBy ;
    private Integer printedBy ;
    private String paymentMethod ;
    private double vatRefundAmount ;
    private String transactionId ;
    private boolean status ;
    private Integer saleType;

    private Double transportation ;
    private int transportationTaxType;
    private Double insurance ;
    private int insuranceTaxType;
    private Double loading ;
    private int loadingTaxType;
    private Double other ;
    private int otherTaxType;

}
