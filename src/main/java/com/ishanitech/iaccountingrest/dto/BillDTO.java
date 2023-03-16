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
public class BillDTO {
    private int id;
    private Date date;
    private int userId;
    private int custId;
    private int companyId;
    private boolean deleted;

}
