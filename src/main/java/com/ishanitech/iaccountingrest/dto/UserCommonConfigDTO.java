package com.ishanitech.iaccountingrest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCommonConfigDTO {

    private int userId;
    private String firstName;

    private String LastName;

    private String email;

    private Long phone;

    private boolean companyStatus;

    private String companyName;
//    private String mobileNumber;
//    private String password;
//    private int accountType;
//    private int wardNo;
}
