package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class Student {

    @GetMapping("/")
    public ResponseDTO<String> getHelloMessage(){
        return new ResponseDTO<>("Hello world form iaccouting");
    }
}
