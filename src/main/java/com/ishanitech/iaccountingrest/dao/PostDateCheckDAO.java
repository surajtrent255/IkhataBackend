package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PostDateCheckDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Date;
import java.util.List;

public interface PostDateCheckDAO {
    @SqlQuery("SELECT * FROM post_date_check WHERE payment_id = :paymentId ")
    @RegisterBeanMapper(PostDateCheckDTO.class)
    List<PostDateCheckDTO> getAllPostCheckInfo(@Bind long paymentId);

    @SqlUpdate("INSERT INTO post_date_check( " +
            "   payment_id, pay_date , pay_date_nepali ) " +
            " VALUES (  :paymentId, :payDate , :payDateNepali );")
    @RegisterBeanMapper(PostDateCheckDTO.class)
    Integer addPostChequeInfo( @Bind long paymentId, @Bind Date payDate ,@Bind Date payDateNepali);

    @SqlUpdate("DELETE FROM post_date_check WHERE payment_id= :paymentId")
    @Nullable
    void DeletePostDateCheckInfo(@Bind int paymentId);

    @SqlUpdate("""
            UPDATE post_date_check
            	SET   pay_date=:payDate
            	WHERE  payment_id = :paymentId
            """)
    void updatePostDateCheck(@Bind long paymentId,@Bind Date payDate);
}
