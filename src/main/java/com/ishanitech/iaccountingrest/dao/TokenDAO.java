package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.model.Token;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;
import java.util.Optional;

public interface TokenDAO {

    @SqlQuery("SELECT t.id AS id, t.token AS token, t.token_type AS token_type, t.revoked AS revoked, "
            +" t.expired AS expired, t.user_id AS user_id FROM token t "
            +" WHERE t.user_id = :id")
    @RegisterBeanMapper(Token.class)
    List<Token> findAllValidTokenByUser(Integer id);

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO token (token, token_type, revoked, expired, user_id)"
            +" VALUES (:token, :tokenType, :revoked, :expired, :userId)")
    int saveToken(@BindBean Token token);

    @Transaction
    @SqlBatch("INSERT INTO `token`(token, token_type, revoked, expired, user_id)"
            +" VALUES (:token, :tokenType, :revoked, :expired, :userId")
    void saveAllTokens(@BindBean List<Token> tokens);

    @SqlQuery("SELECT t.id AS id, t.token AS token, t.token_type AS token_type, t.revoked AS revoked, t.expired AS expired, t.user_id AS user_id FROM token t  WHERE t.token = :jwt ; ")
    @RegisterBeanMapper(Token.class)
    Token findByToken(String jwt);

    @SqlUpdate("UPDATE token SET token = :token, token_type = :tokenType, revoked = :revoked, expired = :expired, user_id = :userId WHERE id = :tokenId ;")
    void updateToken(@BindBean Token token, int tokenId);

    @SqlUpdate("UPDATE token SET  revoked = 1, expired = 1 WHERE user_id = :userId ;")
    void updateTokens(@Bind int userId);
}
