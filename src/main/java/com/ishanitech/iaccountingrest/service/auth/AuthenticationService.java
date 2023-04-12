package com.ishanitech.iaccountingrest.service.auth;

import com.ishanitech.iaccountingrest.dto.AuthenticationRequestDTO;
import com.ishanitech.iaccountingrest.dto.AuthenticationResponseDTO;
import com.ishanitech.iaccountingrest.dto.RegisterRequestDTO;
import com.ishanitech.iaccountingrest.model.Token;
import com.ishanitech.iaccountingrest.model.User;
import com.ishanitech.iaccountingrest.service.TokenService;
import com.ishanitech.iaccountingrest.service.UserService;
import com.ishanitech.iaccountingrest.utils.auth.RoleTypes;
import com.ishanitech.iaccountingrest.utils.auth.TokenTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final TokenService tokenService;
  public AuthenticationResponseDTO register(RegisterRequestDTO request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
            .phone(request.getPhone())
        .password(passwordEncoder.encode(request.getPassword()))
        .build();

    int addedUserId = userService.addUser(user, Integer.parseInt(RoleTypes.USER.getRoleId()));
    User savedUser = userService.getUserById(addedUserId);
    var jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponseDTO.builder()
            .token(jwtToken)
            .user(savedUser)
            .build();
  }

  public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
            request.getPassword());
    authenticationManager.authenticate(
            usernamePasswordAuthenticationToken
    );
    var user = userService.getUserByUsername(request.getEmail()).get();
    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponseDTO.builder()
        .token(jwtToken)
        .user(user)
        .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .userId(user.getId())
        .token(jwtToken)
        .tokenType(TokenTypes.BEARER.toString())
        .expired(false)
        .revoked(false)
        .build();
      tokenService.saveToken(token);
  }

  private void revokeAllUserTokens(User user) {
      tokenService.updateTokens(user.getId());
  }
}
