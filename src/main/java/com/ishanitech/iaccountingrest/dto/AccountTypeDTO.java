package com.ishanitech.iaccountingrest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.Name;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeDTO {
    private int Id;
    private  String Name;


}
