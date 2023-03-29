package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/userconfig")
@RequiredArgsConstructor
public class UserConfigurationController {

    private final UserService userService;

    @GetMapping
    public ResponseDTO<?> getCompanyConfigurationDetails(){
        return new ResponseDTO<>(userService.getUserConfigurationDetails());
    }
}
