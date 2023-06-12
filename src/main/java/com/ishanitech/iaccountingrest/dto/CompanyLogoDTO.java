package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyLogoDTO {

    private int id;

    private String imageName;

   private String imageUrl;

    private int companyId;


}
