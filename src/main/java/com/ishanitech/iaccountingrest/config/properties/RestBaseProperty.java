
package com.ishanitech.iaccountingrest.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rest")
@Data
public class RestBaseProperty {
	private String protocol;
	private String domain;
	private String port;
	private String resourceLocation;
}
