package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankListDTO {
    private int Id;
    private  String Name;
    private  String Location;
}
