package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfigurationDTO {

    private String firstname;

    private String lastname ;

    private String email;

    private int companyId;

    private int userId;

    private String Companyname;

    private int roleId;

    private boolean status;

    private String role;



}
