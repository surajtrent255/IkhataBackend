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
    private int userId;
    private int custId;
    private int companyId;
    private String fiscalYear ;
    private int billNo ;
    private boolean billPrinted;
    private boolean billActive;
    private boolean realtime;
    private String customerName ;
    private String customerPan ;
    private Date billDate  ;
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


}
