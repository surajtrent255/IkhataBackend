package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFeatureDTO {

    private String firstName;

    private String lastName ;

    private String email;

    private Long phone;

    private int companyId;

    private String feature;

    private int userId;

    private int featureId;

    private boolean status;
}
