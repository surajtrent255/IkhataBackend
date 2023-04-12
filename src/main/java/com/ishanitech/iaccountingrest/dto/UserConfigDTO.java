package com.ishanitech.iaccountingrest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserConfigDTO {

    private int userId;
    private String firstName;

    private String LastName;

    private String email;

    private boolean companyStatus;
//    private String mobileNumber;
//    private String password;
//    private int accountType;
//    private int wardNo;
}
