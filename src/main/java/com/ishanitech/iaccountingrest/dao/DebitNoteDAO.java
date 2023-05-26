package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface DebitNoteDAO {

    @SqlUpdate(" INSERT INTO debit_note( " +
            " pan_number, receiver_name, receiver_address, bill_number, date, total_amount, total_tax,company_id,branch_id) " +
            " VALUES (:panNumber, :receiverName, :receiverAddress, :billNumber, :date, :totalAmount, :totalTax,:companyId,:branchId);")
    @RegisterBeanMapper(DebitNoteDTO.class)
    void addDebitNote(@BindBean DebitNoteDTO debitNoteDTO);


    @SqlUpdate("INSERT INTO debit_note_details( " +
            "  serial_number,product_id, product_name, debit_reason, debit_amount, debit_tax_amount,company_id,branch_id,bill_number) " +
            " VALUES (:serialNumber,:productId, :productName, :debitReason, :debitAmount, :debitTaxAmount,:companyId ,:branchId,:billNumber);")
    @RegisterBeanMapper(DebitNoteDetailsDTO.class)
    void addDebitNoteDetails(@BindBean DebitNoteDetailsDTO debitNoteDetailsDTO);

    @SqlQuery("""
            SELECT * FROM debit_note WHERE company_id = :companyId AND branch_id = :branchId ;
             """)
    @RegisterBeanMapper(DebitNoteDTO.class)
    List<DebitNoteDTO> getDebitNoteInfo(@Bind int companyId, @Bind int branchId);

    @SqlQuery("""
            SELECT * FROM debit_note_details WHERE bill_number = :billNumber;
            """)
    @RegisterBeanMapper(DebitNoteDetailsDTO.class)
    List<DebitNoteDetailsDTO> getDebitNoteDetailsInfo(@Bind Long billNumber);

    @SqlQuery("""
            select * from debit_note dn where dn.company_id = :compId and dn.branch_id = :branchId order by id desc limit :pageTotalItems offset (:offset -1) ;
            """)
    @RegisterBeanMapper(DebitNoteDTO.class)
    List<DebitNoteDTO> getLimitedDebitNotesByCompanyAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);
}
