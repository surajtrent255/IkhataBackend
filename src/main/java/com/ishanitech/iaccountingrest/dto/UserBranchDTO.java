package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBranchDTO {

    private int id;

    private int userId;

    private int branchId;

    private int companyId;

    private boolean status;

    private Date createDate;
}
