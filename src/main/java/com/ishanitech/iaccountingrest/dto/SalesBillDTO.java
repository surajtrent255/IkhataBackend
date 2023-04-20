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
public class SalesBillDTO {
    private int id;
    private int userId;
    private int customerId;
    private int companyId;
    private String fiscalYear ;
    private String billNo ;
    private boolean billPrinted;
    private boolean billActive;
    private boolean realtime;
    private String customerName ;
    private long customerPan ;
    private Date billDate  ;
    private double amount ;
    private double discount ;
    private int discountApproach;
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
    private int branchId;
    private boolean draft;
    private int taxApproach;
    private int customerSearchMethod;
    private int printCount;


}
