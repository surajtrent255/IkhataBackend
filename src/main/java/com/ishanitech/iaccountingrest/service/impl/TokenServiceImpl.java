package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.TokenDAO;
import com.ishanitech.iaccountingrest.model.Token;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenServiceImpl implements TokenService {

    private final DbService dbService;

    @Override
    public List<Token> findAllValidTokenByUser(Integer id) {
        TokenDAO tokenDAO = dbService.getDao(TokenDAO.class);
        List<Token> validTokens = new ArrayList<>();
        try{
            validTokens = tokenDAO.findAllValidTokenByUser(id);
        } catch (JdbiException jdbiException){
            log.error("invalid user id");
        }
        return validTokens;
    }

    @Override
    public int saveToken(Token token) {
        try{
            TokenDAO tokenDAO = dbService.getDao(TokenDAO.class);
            int tokenId =  tokenDAO.saveToken(token);
            return tokenId;
        } catch(JdbiException jdbiException){
            log.error("token could not be saved ");
        }
        return 0;
    }

    @Override
    public void updateToken(Token token, int id) {
        try{
            TokenDAO tokenDAO = dbService.getDao(TokenDAO.class);
            tokenDAO.updateToken(token, id);
        } catch(JdbiException jdbiException){
            log.error("token could not be updated ");
        }
    }

    @Override
    public void updateTokens(int userId) {
        try{
            TokenDAO tokenDAO = dbService.getDao(TokenDAO.class);
            tokenDAO.updateTokens(userId);
        } catch(JdbiException jdbiException){
            log.error("token could not be updated ");
        }
    }


    @Override
    public void saveAllTokens(List<Token> tokens) {
        try{
            TokenDAO tokenDAO = dbService.getDao(TokenDAO.class);
            tokenDAO.saveAllTokens(tokens);
        } catch(JdbiException jdbiException){
            log.error("tokens could not be saved");
        }
    }

    @Override
    public Optional<Token> findByToken(String jwt) {
        Token token = null;
        try{
            TokenDAO tokenDAO = dbService.getDao(TokenDAO.class);
            token = tokenDAO.findByToken(jwt);
        } catch(JdbiException jdbiException){
            log.error("tokens could not be saved");
        }
        return Optional.ofNullable(token);
    }
}
