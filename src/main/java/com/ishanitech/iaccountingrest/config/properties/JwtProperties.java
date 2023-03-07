package com.ishanitech.iaccountingrest.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class JwtProperties {
	private String applicationName;
	private long expirationDate;
	private SecurityProperty security;
	private JwtAuthenticationProperty auth;
}
