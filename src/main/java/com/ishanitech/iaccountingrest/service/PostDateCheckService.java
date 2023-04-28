package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PostDateCheckDTO;

import java.util.Date;
import java.util.List;

public interface PostDateCheckService {
    List<PostDateCheckDTO> getAllPostChequeInfo();

    Integer addPostChequeInfo(long chequeNo, Long paymentId, Date payDate);

    void deletePostDateCheckInfo(int paymentId);
}
