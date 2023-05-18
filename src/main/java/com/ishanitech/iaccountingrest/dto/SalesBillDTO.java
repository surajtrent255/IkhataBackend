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
    private Integer id;
    private Integer userId;
    private Integer customerId;
    private Integer companyId;
    private String fiscalYear ;
    private String billNo ;
    private boolean billPrinted;
    private boolean billActive;
    private boolean realtime;
    private String customerName ;
    private long customerPan ;
    private Date billDate  ;
    private Double amount ;
    private Double discount ;
    private Integer discountApproach;
    private Double taxableAmount ;
    private Double taxAmount ;
    private Double  totalAmount ;
    private boolean syncWithIrd;
    private String printedTime ;
    private String enteredBy ;
    private Integer printedBy ;
    private String paymentMethod ;
    private Double vatRefundAmount ;
    private String transactionId ;
    private boolean status ;
    private Integer branchId;
    private Integer counterId;
    private boolean draft;
    private Integer taxApproach;
    private Integer customerSearchMethod;
    private Integer printCount;
    private Integer saleType;
    private boolean hasAbbr;



}
