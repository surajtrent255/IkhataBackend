package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCounterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int counterId;
    private int companyId;
    private int branchId;
    private int userId;
    private boolean userStatus;
    private boolean counterStatus;
}
