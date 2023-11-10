package com.ishanitech.iaccountingrest.controller.auth;

import com.ishanitech.iaccountingrest.dto.AuthenticationRequestDTO;
import com.ishanitech.iaccountingrest.dto.AuthenticationResponseDTO;
import com.ishanitech.iaccountingrest.dto.RegisterRequestDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.EntityNotFoundException;
import com.ishanitech.iaccountingrest.service.UserService;
import com.ishanitech.iaccountingrest.service.auth.AuthenticationService;
import com.ishanitech.iaccountingrest.service.auth.LogoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.management.RuntimeMBeanException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

  private final AuthenticationService service;
  private final LogoutService logoutService;
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDTO> register(
      @RequestBody RegisterRequestDTO request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseDTO<?> authenticate(
      @RequestBody AuthenticationRequestDTO request) {
    try {
      return new ResponseDTO<>(service.authenticate(request));
    } catch (Exception ex) {
      log.info(ex.getMessage());
      throw new EntityNotFoundException("invalid login credential");
    }

  }

  @PostMapping("/enterEmailForForgotPassword")
  public void enterEmailForForgotPassword(@RequestBody Map<String, String> email) {
    try {
      userService.checkEmailAndGenerateToken(email.get("email"));
    } catch (Exception ex) {
      log.error(ex.getMessage());
      throw new RuntimeException("some thing went wrong" + ex.getMessage());
    }
  }

  @PostMapping("/reset-password")
  public ResponseDTO<String> resetPassword(@RequestBody Map<String, String> tokenAndPassword){
    try{
      String email  = userService.resetPassword(tokenAndPassword);
    } catch(Exception ex){
      log.error("something wnet wrong while reseting password " ,  ex.getMessage());
      throw new RuntimeException(" something went wrong while reseting password " + ex.getMessage());
    }
    return null;
  }

}