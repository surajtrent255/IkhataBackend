package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypePaymentDTO {
    private int Id;
    private String Name;
}
