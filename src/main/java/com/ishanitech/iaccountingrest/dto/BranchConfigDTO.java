package com.ishanitech.iaccountingrest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchConfigDTO {

    private int userId;
    private String firstName;

    private String lastName ;

    private String email;

    private Long userPhone;

    private String branchName ;

    private String branchDistrict;

    private Long branchPhone;

    private int branchId;

    private boolean branchStatus;



}
