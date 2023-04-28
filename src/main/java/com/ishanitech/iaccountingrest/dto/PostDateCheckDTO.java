package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDateCheckDTO {
    private int SN;
    private Long checkNo;

    private int paymentId;

    private Date payDate;

    private boolean status;
}
