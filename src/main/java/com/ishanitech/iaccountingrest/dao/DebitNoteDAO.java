package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DebitNoteDAO {

    @SqlUpdate(" INSERT INTO debit_note( " +
            " pan_number, receiver_name, receiver_address, bill_number, date, total_amount, total_tax,company_id) " +
            " VALUES (:panNumber, :receiverName, :receiverAddress, :billNumber, :date, :totalAmount, :totalTax,:companyId);")
    @RegisterBeanMapper(DebitNoteDTO.class)
    void addDebitNote(@BindBean DebitNoteDTO debitNoteDTO);


    @SqlUpdate("INSERT INTO debit_note_details( " +
            "  sn,product_id, product_name, debit_reason, debit_amount, debit_tax_amount,company_id) " +
            " VALUES (:SN,:productId, :productName, :debitReason, :debitAmount, :debitTaxAmount,:companyId );")
    @RegisterBeanMapper(DebitNoteDetailsDTO.class)
    void addDebitNoteDetails(@BindBean DebitNoteDetailsDTO debitNoteDetailsDTO);


}
