package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
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
            " sb.draft as draft, "+
            " sb.status as status, "+
            "  sb.printed_by as printed_by,  " +
            "  sb.is_realtime as realtime,  " +
            "  sb.payment_method as payment_method,  " +
            "  sb.vat_refund_amount  as vat_refund_amount,  " +
            "  sb.transaction_id  as transaction_id,  " +
            " sb.sale_type as sale_type"+
            "    " +
            " from sales_bill sb  ;")
    @RegisterBeanMapper(SalesBillDTO.class)
    List<SalesBillDTO> getAllBills();

    @SqlQuery("select   " +
            " sb.id as id, "+
            "  sb.fiscal_year as  fiscal_year,  " +
            "  sb.bill_no as bill_no,  " +
            "  sb.customer_id as customer_id, "+
            "  sb.customer_name as customer_name,  " +
            "  sb.customer_pan as customer_pan,  " +
            "  sb.bill_date as bill_date,   " +
            "  sb.amount  as amount,  " +
            "  sb.discount  as discount,  " +
            "  sb.discount_approach as discount_approach, "+
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
            "  sb.company_id as company_id, "+
            " sb.branch_id as branch_id, "+
            " sb.draft as draft, "+
            " sb.status as status, "+
            " sb.tax_approach as taxApproach," +
            " sb.transaction_id  as transaction_id , " +
            " sb.customer_search_method as customerSearchMethod, " +
            " sb.sale_type as sale_type,"+
            " sb.has_abbr as has_abbr"+
            " from sales_bill sb where  sb.id = :id;")
    @RegisterBeanMapper(SalesBillDTO.class)
    SalesBillDTO getBillById(int id);

    @GetGeneratedKeys
    @SqlUpdate("insert into sales_bill (" +
            " fiscal_year ," +
            " bill_no ," +
            " customer_id, "+
            " customer_name ," +
            " customer_pan ," +
            " bill_date, "+
            " amount  ," +
            " discount  ," +
            " discount_approach , "+
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
            " transaction_id,  " +
            " company_id, "+
            " branch_id, "+
            " counter_id, "+
            " draft, "+
            " tax_approach, "+
            " customer_search_method, "+
            " print_count, "+
            " sale_type, "+
            " has_abbr"+
            ") values (" +
            " :fiscalYear," +
            " :billNo ," +
            " :customerId, "+
            " :customerName ," +
            " :customerPan ," +
            " :billDate, "+
            " :amount  ," +
            " :discount  ," +
            " :discountApproach, "+
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
            " :transactionId , " +
            " :companyId , "+
            " :branchId, "+
            " :counterId,"+
            " :draft, "+
            " :taxApproach, "+
            " :customerSearchMethod,"+
            " :printCount,"+
            " :saleType,"+
            " :hasAbbr"+
            ")")
     int addNewBill(@BindBean SalesBillDTO salesBillDTO);


    @SqlUpdate("UPDATE sales_bill SET status = false WHERE id = :billId")
    void deleteBillById(int  billId);

    @SqlUpdate("delete from sales_bill where id = :billId")
    void permanentBillDeleteById(int billId);

    @SqlUpdate("update sales_bill set is_bill_printed = true, print_count = print_count+1,  printed_time = :date , printed_by = :printerId" +
            " where id = :billId")
    int printTheBillWithBillId(@Bind int billId, @Bind Date date, @Bind int printerId);


    @SqlQuery("select   " +
            "  sb.fiscal_year as  fiscal_year,  " +
            " sb.id as id, "+
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
            "  sb.transaction_id  as transaction_id, " +
            "  sb.company_id as company_id, " +
            "  sb.draft as draft, " +
            " sb.status as status, "+
            " sb.sale_type as sale_type"+
            " from sales_bill sb where  sb.company_id = :compId and sb.branch_id = :branchId;")
    @RegisterBeanMapper(SalesBillDTO.class)
    List<SalesBillDTO> getSalesBillByCompanyId(int compId, int branchId);

    @SqlQuery("""
            select * from sales_bill sb where <caseQuery> ;
            """)
    @RegisterBeanMapper(SalesBillDTO.class)
    List<SalesBillDTO> getLimitedSalesBillByCompanyAndBranchId(@Define String caseQuery);

    @SqlUpdate("update sales_bill set bill_no = :billNo , draft=false where id = :billId")
    void makeDraftFalse(String billNo, int billId);
    //mj printedBy ? id or name

}
