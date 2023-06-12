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
    private int paymentId;

    private Date payDate;

    private Date payDateNepali;

    private boolean status;
}
