package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.UserDAO;
import com.ishanitech.iaccountingrest.dto.EmailDTO;
import com.ishanitech.iaccountingrest.dto.ForgotPasswordDTO;
import com.ishanitech.iaccountingrest.model.Role;
import com.ishanitech.iaccountingrest.model.User;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.EmailService;
import com.ishanitech.iaccountingrest.service.MailService;
import com.ishanitech.iaccountingrest.service.UserService;
import com.ishanitech.iaccountingrest.service.auth.AuthenticationService;
import com.ishanitech.iaccountingrest.utils.EncodePassword;

import org.springframework.security.crypto.password.PasswordEncoder;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final DbService dbService;
    private final MailService mailService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${server.ip}")
    private  String serverIp;

    @Override
    public Optional<User> getUserByUsername(String username) {
        UserDAO userDao = dbService.getDao(UserDAO.class);
        User user = null;
        try {
            user = userDao.getUserByEmail(username).get();
        } catch (UnableToExecuteStatementException ex) {
            log.info("Exception occured: " + ex.getMessage());
        }

        if (user != null) {
            return Optional.of(user);
        }

        throw new UsernameNotFoundException("Incorrect Credentials!");
    }

    @Override
    public User getUserById(int userId) {
        UserDAO userDao = dbService.getDao(UserDAO.class);
        User user = null;
        try {
            user = userDao.getUserById(userId).get();
        } catch (UnableToExecuteStatementException ex) {
            log.info("Exception occured " + ex.getMessage());
        }
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("userId is not valid!");

    }

    @Override
    public int addUser(User user, int roleId) {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        int savedUserId = 0;
        try {
            savedUserId = userDAO.addUserWithRole(user, roleId);
        } catch (JdbiException jdbiException) {
            log.error("error occured while adding user");
        }
        return savedUserId;
    }

    @Override
    public List<Role> getAllRole() {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        return userDAO.getAllRole();
    }

    @Override
    @Transactional
    public void checkEmailAndGenerateToken(String email) {
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        User user = userDAO.getUserByEmail(email).orElse(null);
        if (user != null) {
            String uniqueID = UUID.randomUUID().toString();
            ForgotPasswordDTO forgotPassword = new ForgotPasswordDTO();
            forgotPassword.setUserEmail(email);
            forgotPassword.setStatus(true);
            forgotPassword.setToken(uniqueID);
            // already exist check remaining
            userDAO.removePreviousToken(email);
            userDAO.generateTokenForForgetPassword(forgotPassword);

            // mailService.sendEmail(text, "suraj.trent255@gmail.com");
            EmailDTO emailingDetail = new EmailDTO();
            emailingDetail.setFrom("no-reply@ishanitech.com");
            emailingDetail.setTo(email);
            emailingDetail.setSubject("Password Reset !");
            Map<String, Object> map = new HashMap<>();
            map.put("name", user.getFirstname() + " " + user.getLastname());

            map.put("email", user.getEmail());
            map.put("token", uniqueID);
            String serverIp = this.serverIp;
            map.put("resetLink", serverIp+"/reset-password/" + uniqueID + "/" + email);
            emailingDetail.setModel(map);
            try {
                emailService.sendAccountActivationEmail(emailingDetail, "pw");
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (TemplateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log.info("email has been sent");
        } else {
            throw new RuntimeException("Something went wrong !! username may be incorrect");
        }
    }

    @Override
    public String resetPassword(Map<String, String> tokenAndPassword) {
        // TODO Auto-generated method stub
        String token = tokenAndPassword.get("token");
        String resetPassword = tokenAndPassword.get("newPassword");
        String email = tokenAndPassword.get("email");
        UserDAO userDAO = dbService.getDao(UserDAO.class);
        User user = userDAO.getUserByEmail(email).orElse(null);
        ForgotPasswordDTO forgotPasswordDto = userDAO.getForgotPasswordDto(email);
        if (forgotPasswordDto.getToken().equals(token)) {
            String newCryptedPassword = passwordEncoder.encode(resetPassword);
           userDAO.resetPassword(newCryptedPassword, email);

            return email;

        } else {
            throw new RuntimeException("something went wrong while reseting password");
        }

    }

}
