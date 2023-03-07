package com.ishanitech.iaccountingrest.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ishanitech.iaccountingrest.dto.LoginDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.dto.UserDTO;
import com.ishanitech.iaccountingrest.exception.ApiError;
import com.ishanitech.iaccountingrest.utils.JsonTokenHelper;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private ObjectMapper objectMapper;
	private JsonTokenHelper tokenHelper;
	private final String TOKEN_SCHEMA_PREFIX = "Bearer ";
	private final String TOKEN_HEADER = "Authorization";

	public TokenAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper,
			JsonTokenHelper tokenHelper) {
		this.authenticationManager = authenticationManager;
		this.objectMapper = objectMapper;
		this.tokenHelper = tokenHelper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		LoginDTO loginData;
		try {
			loginData = objectMapper.readValue(request.getInputStream(), LoginDTO.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUsername(),
					loginData.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserDTO user = new UserDTO();
		user = ((CustomUserDetails) authResult.getPrincipal()).getUser();
		response.setContentType("application/json");
		response.setStatus(HttpStatus.OK.value());
		String jwtToken = tokenHelper.generateToken(user);
		response.addHeader("Access-Control-Expose-Headers", TOKEN_HEADER);
		response.setHeader(TOKEN_HEADER, TOKEN_SCHEMA_PREFIX + jwtToken);
		ResponseDTO<UserDTO> userResponse = new ResponseDTO<UserDTO>(user);
		objectMapper.writeValue(response.getOutputStream(), userResponse);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		if (!request.getMethod().equalsIgnoreCase("post")) {
			response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			response.setContentType("application/json");
			objectMapper.writeValue(response.getOutputStream(),
					String.format("%s method is not allowed.", request.getMethod()));
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json");
			ApiError apiError = new ApiError.Builder(HttpStatus.UNAUTHORIZED.value())
					.withTime(LocalDateTime.now())
					.withMessage(failed.getLocalizedMessage())
					.withDescription("Authentication failed! Please re-enter correct credentials.")
					.build();
			objectMapper.writeValue(response.getOutputStream(), apiError);
		}

	}

}
