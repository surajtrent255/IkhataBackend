package com.ishanitech.iaccountingrest.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "database")
public class DataSourceBean {
	private String username;
	private String password;
	private String databaseName;
	private String port;
	private String driverClassName;
	private String databaseType;
}
