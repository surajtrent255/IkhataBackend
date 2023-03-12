package com.ishanitech.iaccountingrest.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.ishanitech.iaccountingrest.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO implements Serializable {
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	@JsonIgnore
	private String password;
	private String mobileNumber;
	private boolean isLocked;
	private boolean enabled;
	private boolean firstLogin;
	private boolean expired;
	private Date registeredDate;
	private Set<Role> roles = new HashSet<>();;
	private String stamp;
	private String signature;
}
