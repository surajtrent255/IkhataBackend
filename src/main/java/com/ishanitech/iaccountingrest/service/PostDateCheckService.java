package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PostDateCheckDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;

import java.util.Date;
import java.util.List;

public interface PostDateCheckService {
    List<PostDateCheckDTO> getAllPostChequeInfo(long paymentId);

    Integer addPostChequeInfo( Long paymentId, Date payDate,Date payDateNepali);

    void deletePostDateCheckInfo(int paymentId);
    void updatePostDateCheck( long paymentId,   Date payDate);

}
