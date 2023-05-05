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
    @SqlQuery("SELECT * FROM post_date_check ")
    @RegisterBeanMapper(PostDateCheckDTO.class)
    List<PostDateCheckDTO> getAllPostCheckInfo();

    @SqlUpdate("INSERT INTO post_date_check( " +
            "  check_no, payment_id, pay_date) " +
            " VALUES ( :checkNo, :paymentId, :payDate);")
    @RegisterBeanMapper(PostDateCheckDTO.class)
    Integer addPostChequeInfo(@Bind long checkNo, @Bind long paymentId, @Bind Date payDate);

    @SqlUpdate("DELETE FROM post_date_check WHERE payment_id= :paymentId")
    @Nullable
    void DeletePostDateCheckInfo(@Bind int paymentId);
}
