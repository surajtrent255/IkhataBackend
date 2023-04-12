package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfigurationDTO {

    private String firstName;

    private String lastName ;

    private String email;

    private int companyId;

    private int userId;

    private String companyName;

    private int roleId;

    private boolean companyStatus;

    private boolean roleStatus;

    private String role;



}
