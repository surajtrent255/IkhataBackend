package com.ishanitech.iaccountingrest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserStatusDTO {
    private boolean status;
    private int userId;
}
