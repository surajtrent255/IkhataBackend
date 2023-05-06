package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DebitNoteDAO {

    @SqlUpdate(" INSERT INTO debit_note( " +
            " pan_number, receiver_name, receiver_address, bill_number, date, total_amount, total_tax) " +
            " VALUES (:panNumber, :receiverName, :receiverAddress, :billNumber, :date, :totalAmount, :totalTax);")
    @RegisterBeanMapper(DebitNoteDTO.class)
    void addDebitNote(@BindBean DebitNoteDTO debitNoteDTO);


    @SqlUpdate("INSERT INTO debit_note_details( " +
            "  product_id, product_name, debit_reason, debit_amount, debit_tax_amount) " +
            " VALUES (:productId, :productName, :debitReason, :debitAmount, debitTaxAmount );")
    @RegisterBeanMapper(DebitNoteDetailsDTO.class)
    void addDebitNoteDetails(@BindBean DebitNoteDetailsDTO debitNoteDetailsDTO);


}
