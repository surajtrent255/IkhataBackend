package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.model.Token;

import java.util.List;
import java.util.Optional;

public interface TokenService {
    List<Token> findAllValidTokenByUser(Integer id);
    int  saveToken(Token token);
    void updateToken(Token token, int id);
    void updateTokens(int userId);
    void saveAllTokens(List<Token> tokens);
    Optional<Token> findByToken(String jwt);
}
