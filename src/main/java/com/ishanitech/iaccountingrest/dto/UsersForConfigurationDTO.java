package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersForConfigurationDTO {

    private String firstname;

    private String lastname;

    private String email;

    private boolean status;

}
