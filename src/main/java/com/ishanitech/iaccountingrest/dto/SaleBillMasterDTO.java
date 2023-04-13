package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleBillMasterDTO {
//    private int productId;
//    private int qty;
//    private Date date;
//    private double discountPerUnit;
//    private double rate;
//    private int companyId;
//    private int userId;
//    private int custId;
//    private String fiscalYear ;
//    private String billNo ;
//    private String customerName ;
//    private String customerPan ;
//    private Date billDate  ;
//    private double amount ;
//    private boolean syncWithIrd;
//    private double discount ;
//    private double taxableAmount ;
//    private double taxAmount ;
//    private double  totalAmount ;
//    private boolean isBillPrinted ;
//    private boolean isBillActive ;
//    private String printedTime ;
//    private String enteredBy ;
//    private String printedBy ;
//    private boolean isRealtime ;
//    private String paymentMethod ;
//    private double vatRefundAmount ;
//    private String transactionId ;
//    private boolean status ;
    private List<SalesBillDetailDTO> salesBillDetails;
    private SalesBillDTO salesBillDTO;
    private int alreadyDraft;
}
