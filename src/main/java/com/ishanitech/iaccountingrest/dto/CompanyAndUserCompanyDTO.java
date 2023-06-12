package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAndUserCompanyDTO {
    private int companyId;

    private String name;

    private String description;

    private Long panNo;

    private int state;

    private String district;

    private String munVdc;

    private int wardNo;

    private String email;

    private Long phone;

    private int userId;

    private int imageId;

    private String imageName;

    private String imageUrl;

    private String ownerName;

    private Long landlineNumber;

    private String registrationType;

    private Date createdDate;

    private String createdDateNepali;


}
