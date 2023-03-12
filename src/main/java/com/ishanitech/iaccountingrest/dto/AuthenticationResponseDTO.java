package com.ishanitech.iaccountingrest.dto;

import com.ishanitech.iaccountingrest.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO {

  private String token;
  private User user;
}
