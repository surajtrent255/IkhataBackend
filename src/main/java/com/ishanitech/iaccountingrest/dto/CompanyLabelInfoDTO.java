package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyLabelInfoDTO {

    private int companyLabelId;

    private int companyLabelDataId;

    private String text;

    private int labelId;

    private int companyId;

    private String name;
}
