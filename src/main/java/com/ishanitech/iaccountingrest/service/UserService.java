package com.ishanitech.iaccountingrest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserDTO;
import com.ishanitech.iaccountingrest.dto.UserRegistrationDTO;
import com.ishanitech.iaccountingrest.model.User;
/**
 * 
 * @author Umesh Bhujel
 *
 */
public interface UserService {
	Optional<User> getUserByUsername(String username);
	User getUserById(int userId);
	int addUser(User user, int roleId);

	List<UserConfigurationDTO> getUserConfigurationDetails(int companyId);
}