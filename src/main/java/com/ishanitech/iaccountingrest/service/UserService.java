package com.ishanitech.iaccountingrest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.ishanitech.iaccountingrest.model.Role;
import com.ishanitech.iaccountingrest.model.User;

public interface UserService {
	Optional<User> getUserByUsername(String username);
	User getUserById(int userId);
	int addUser(User user, int roleId);

	List<Role> getAllRole();
    void checkEmailAndGenerateToken(String email);
    String resetPassword(Map<String, String> tokenAndPassword);
}