package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PostDateCheckDAO;
import com.ishanitech.iaccountingrest.dto.PostDateCheckDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PostDateCheckService;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
public class PostDateChequeServiceImpl implements PostDateCheckService {
    @Autowired
    private  DbService dbService;
    @Override
    public List<PostDateCheckDTO> getAllPostChequeInfo(long paymentId) {
        PostDateCheckDAO postDateCheckDAO = dbService.getDao(PostDateCheckDAO.class);
        return postDateCheckDAO.getAllPostCheckInfo(paymentId);
    }

    @Override
    public Integer addPostChequeInfo( Long paymentId, Date payDate,Date payDateNepali) {
        PostDateCheckDAO postDateCheckDAO = dbService.getDao(PostDateCheckDAO.class);

        try{
            postDateCheckDAO.addPostChequeInfo(paymentId,payDate,payDateNepali);

        }catch (JdbiException jdbiException){
            log.error(jdbiException.getMessage());
        }
        return 1;
    }

    @Override
    public void deletePostDateCheckInfo(int paymentId) {
        PostDateCheckDAO postDateCheckDAO = dbService.getDao(PostDateCheckDAO.class);
        postDateCheckDAO.DeletePostDateCheckInfo(paymentId);
    }

    @Override
    public void updatePostDateCheck(long paymentId,  Date payDate) {
        PostDateCheckDAO postDateCheckDAO = dbService.getDao(PostDateCheckDAO.class);
        postDateCheckDAO.updatePostDateCheck(paymentId,payDate);
    }
}
