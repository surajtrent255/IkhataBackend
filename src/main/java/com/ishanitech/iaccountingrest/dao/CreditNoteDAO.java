package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Date;
import java.util.List;

public interface CreditNoteDAO {

    @SqlUpdate(" INSERT INTO credit_note( " +
            " pan_number, customer_name, customer_address, bill_number, date,nepali_date, total_amount, total_tax,company_id,branch_id,fiscal_year) " +
            " VALUES (:panNumber, :customerName, :customerAddress, :billNumber, :date,:nepaliDate, :totalAmount, :totalTax,:companyId,:branchId,:fiscalYear );")
    @RegisterBeanMapper(CreditNoteDTO.class)
    void addCreditNote(@BindBean CreditNoteDTO creditNoteDTO);

    @SqlUpdate("INSERT INTO credit_note_details( " +
            " serial_number, product_id, product_name,product_qty,product_unit, credit_reason, credit_amount,total_credit_Amount, credit_tax_amount , company_id,branch_id,bill_number) " +
            " VALUES (:serialNumber,:productId, :productName,:productQty,:productUnit, :creditReason, :creditAmount,:totalCreditAmount, :creditTaxAmount,:companyId,:branchId,:billNumber );")
    @RegisterBeanMapper(CreditNoteDetailsDTO.class)
    void addCreditNoteDetails(@BindBean CreditNoteDetailsDTO creditNoteDetailsDTO);

    @SqlQuery("""
            SELECT DISTINCT * FROM credit_note WHERE company_id = :companyId AND branch_id = :branchId;
            """)
    @RegisterBeanMapper(CreditNoteDTO.class)
    List<CreditNoteDTO> getCreditNoteInfo(@Bind int companyId,@Bind int branchId);

    @SqlQuery("""
            select  * from credit_note cn where <caseQuery>
            """)
    @RegisterBeanMapper(CreditNoteDTO.class)
    List<CreditNoteDTO> searchCreditNoteBySearchInput(@Define String caseQuery);

    @SqlQuery("""
            SELECT * FROM credit_note_details WHERE bill_number = :billNumber;
            """)
    @RegisterBeanMapper(CreditNoteDetailsDTO.class)
    List<CreditNoteDetailsDTO> getCreditNoteDetailInfo(@Bind String billNumber);

}
