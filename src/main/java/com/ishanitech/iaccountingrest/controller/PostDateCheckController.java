package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PostDateCheckDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.PostDateCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/cheque")
@RequiredArgsConstructor
public class PostDateCheckController {

    private final PostDateCheckService postDateCheckService;

    @GetMapping
    public ResponseDTO<?> getAllPostChequeInfo(){
        try {
            return new ResponseDTO<>(postDateCheckService.getAllPostChequeInfo());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("data");
    }



}
