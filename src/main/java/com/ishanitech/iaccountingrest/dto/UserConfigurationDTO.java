package com.ishanitech.iaccountingrest.dto;

import com.ishanitech.iaccountingrest.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfigurationDTO {

    private String firstName;

    private String lastName ;

    private String email;

    private Long phone;

    private int companyId;

    private int userId;

    private String companyName;

    private int roleId;

    private boolean companyStatus;

    private boolean roleStatus;

    private String role;



}
