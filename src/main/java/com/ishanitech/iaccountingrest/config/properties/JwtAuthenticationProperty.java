package com.ishanitech.iaccountingrest.config.properties;

import lombok.Data;

@Data
public class JwtAuthenticationProperty {
	private String header;
	private String schema;
}
