package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.ReceiptDTO;
import jakarta.annotation.Nullable;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface ReceiptDAO {

    @SqlQuery("SELECT * FROM receipt WHERE company_id=:companyId")
    @RegisterBeanMapper(ReceiptDTO.class)
    List<ReceiptDTO> getAllReceipts(@Bind int companyId);

    @SqlUpdate("INSERT INTO receipt( " +
            "  company_id, party_id, amount, date,nepali_date, mode_id, tds_deducted_amount, post_date_check, branch_id) " +
            " VALUES ( :companyId, :partyId, :amount, :date,:nepaliDate, :modeId, :tdsDeductedAmount, :postDateCheck, :branchId);")
    Integer addReceipt(@BindBean ReceiptDTO receiptDTO);


    @SqlUpdate("DELETE FROM receipt WHERE sn = :SN")
    @Nullable
    void deleteReceipt(@Bind int SN);

    @SqlQuery("""
            select * from receipt r where r.company_id = :compId and r.branch_id = :branchId order by SN desc limit :pageTotalItems offset (:offset -1) ;
            """)
    @RegisterBeanMapper(ReceiptDTO.class)
    List<ReceiptDTO> getLimitedReceiptsByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
