package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MunicipalityDTO {
    private int municipalityId;

    private String municipalityName;

    private int provinceId;

    private int districtId;

}
