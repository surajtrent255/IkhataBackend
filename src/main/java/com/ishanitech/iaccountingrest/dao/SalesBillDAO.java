package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Date;
import java.util.List;

public interface SalesBillDAO {
    @SqlQuery("select   " +
            "  sb.fiscal_year as  fiscal_year,  " +
            "  sb.bill_no as bill_no,  " +
            "  sb.customer_name as customer_name,  " +
            "  sb.customer_pan as customer_pan,  " +
            "  sb.bill_date as bill_date,   " +
            "  sb.amount  as amount,  " +
            "  sb.discount  as discount,  " +
            "  sb.taxable_amount  as taxable_amount,  " +
            "  sb.tax_amount  as tax_amount,  " +
            "  sb.total_amount  as total_amount,  " +
            "  sb.sync_with_ird as sync_with_ird,  " +
            "  sb.is_bill_printed  as bill_printed,  " +
            "  sb.is_bill_active  as bill_active,  " +
            "  sb.printed_time  as printed_time,  " +
            "  sb.entered_by  as entered_by,  " +
            "  sb.printed_by as printed_by,  " +
            "  sb.is_realtime as realtime,  " +
            "  sb.payment_method as payment_method,  " +
            "  sb.vat_refund_amount  as vat_refund_amount,  " +
            "  sb.transaction_id  as transaction_id  " +
            "    " +
            " from sales_bill sb where sb.status = true ;")
    @RegisterBeanMapper(SalesBillDTO.class)
    List<SalesBillDTO> getAllBills();

    @SqlQuery("select   " +
            "  sb.fiscal_year as  fiscal_year,  " +
            "  sb.bill_no as bill_no,  " +
            "  sb.customer_name as customer_name,  " +
            "  sb.customer_pan as customer_pan,  " +
            "  sb.bill_date as bill_date,   " +
            "  sb.amount  as amount,  " +
            "  sb.discount  as discount,  " +
            "  sb.taxable_amount  as taxable_amount,  " +
            "  sb.tax_amount  as tax_amount,  " +
            "  sb.total_amount  as total_amount,  " +
            "  sb.sync_with_ird as sync_with_ird,  " +
            "  sb.is_bill_printed  as bill_printed,  " +
            "  sb.is_bill_active  as bill_active,  " +
            "  sb.printed_time  as printed_time,  " +
            "  sb.entered_by  as entered_by,  " +
            "  sb.printed_by as printed_by,  " +
            "  sb.is_realtime as realtime,  " +
            "  sb.payment_method as payment_method,  " +
            "  sb.vat_refund_amount  as vat_refund_amount,  " +
            "  sb.transaction_id  as transaction_id  " +
            "    " +
            " from sales_bill sb where sb.status = true and sb.bill_no = :id;")
    @RegisterBeanMapper(SalesBillDTO.class)
    SalesBillDTO getBillById(int id);

    @SqlUpdate("insert into sales_bill (" +
            " fiscal_year ," +
            " bill_no ," +
            " customer_name ," +
            " customer_pan ," +
            " amount  ," +
            " discount  ," +
            " taxable_amount  ," +
            " tax_amount  ," +
            " total_amount  ," +
            " sync_with_ird ," +
            " is_bill_printed  ," +
            " is_bill_active  ," +
            " printed_time  ," +
            " entered_by  ," +
            " printed_by ," +
            " is_realtime ," +
            " payment_method ," +
            " vat_refund_amount  ," +
            " transaction_id  " +
            ") values (" +
            " :fiscalYear," +
            " :billNo ," +
            " :customerName ," +
            " :customerPan ," +
            " :amount  ," +
            " :discount  ," +
            " :taxableAmount  ," +
            " :taxAmount  ," +
            " :totalAmount  ," +
            " :syncWithIrd ," +
            " :billPrinted ," +
            " :billActive ," +
            " :printedTime  ," +
            " :enteredBy  ," +
            " :printedBy ," +
            " :realtime ," +
            " :paymentMethod ," +
            " :vatRefundAmount  ," +
            " :transactionId  " +
            ")")
     void addNewBill(@BindBean SalesBillDTO salesBillDTO);


    @SqlUpdate("UPDATE sales_bill SET status = false WHERE bill_no = :bill_no")
    void deleteBillById(int  bill_no);


    @SqlUpdate("update sales_bill set is_bill_printed = true , printed_time = :date , printed_by = :printerId" +
            " where bill_no = :billNo")
    int printTheBillWithBillId(@Bind int billNo, @Bind Date date, @Bind int printerId);
    //mj printedBy ? id or name

}
