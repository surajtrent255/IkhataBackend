package com.ishanitech.iaccountingrest.controller.auth;

import com.ishanitech.iaccountingrest.dto.AuthenticationRequestDTO;
import com.ishanitech.iaccountingrest.dto.AuthenticationResponseDTO;
import com.ishanitech.iaccountingrest.dto.RegisterRequestDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.exception.EntityNotFoundException;
import com.ishanitech.iaccountingrest.exception.model.UserRegistrationException;
import com.ishanitech.iaccountingrest.service.auth.AuthenticationService;
import com.ishanitech.iaccountingrest.service.auth.LogoutService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.jdbi.v3.core.JdbiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;
  private final LogoutService logoutService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDTO> register(
      @RequestBody RegisterRequestDTO request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseDTO<?> authenticate(
      @RequestBody AuthenticationRequestDTO request
  ) {
    try{
      return new ResponseDTO<>(service.authenticate(request));
    } catch(Exception ex){
        throw new EntityNotFoundException("invalid login credential");
    }

  }

}