package com.ishanitech.iaccountingrest.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Service
public class EncodePassword {
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public String encodePasswordUsingBcrypt(String password){
        
        return passwordEncoder.encode(password);
    }

}
