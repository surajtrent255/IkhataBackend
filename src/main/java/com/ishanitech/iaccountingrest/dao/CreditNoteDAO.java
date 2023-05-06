package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CreditNoteDAO {

    @SqlUpdate(" INSERT INTO credit_note( " +
            " pan_number, customer_name, customer_address, bill_number, date, total_amount, total_tax) " +
            " VALUES (:panNumber, :customerName, :customerAddress, :billNumber, :date, :totalAmount, :totalTax );")
    @RegisterBeanMapper(CreditNoteDTO.class)
    void addCreditNote(@BindBean CreditNoteDTO creditNoteDTO);

    @SqlUpdate("INSERT INTO credit_note_details(\n" +
            "  product_id, product_name, credit_reason, credit_amount, credit_tax_amount) " +
            " VALUES (:productId, :productName, ?:creditReason, :creditAmount, :creditTaxAmount );")
    @RegisterBeanMapper(CreditNoteDetailsDTO.class)
    void addCreditNoteDetails(@BindBean CreditNoteDetailsDTO creditNoteDetailsDTO);

}
