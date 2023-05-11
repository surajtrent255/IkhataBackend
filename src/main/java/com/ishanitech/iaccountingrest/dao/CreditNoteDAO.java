package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface CreditNoteDAO {

    @SqlUpdate(" INSERT INTO credit_note( " +
            " pan_number, customer_name, customer_address, bill_number, date, total_amount, total_tax,company_id) " +
            " VALUES (:panNumber, :customerName, :customerAddress, :billNumber, :date, :totalAmount, :totalTax,:companyId );")
    @RegisterBeanMapper(CreditNoteDTO.class)
    void addCreditNote(@BindBean CreditNoteDTO creditNoteDTO);

    @SqlUpdate("INSERT INTO credit_note_details(\n" +
            " sn, product_id, product_name, credit_reason, credit_amount, credit_tax_amount , company_id) " +
            " VALUES (:SN,:productId, :productName, :creditReason, :creditAmount, :creditTaxAmount,:companyId );")
    @RegisterBeanMapper(CreditNoteDetailsDTO.class)
    void addCreditNoteDetails(@BindBean CreditNoteDetailsDTO creditNoteDetailsDTO);

}
