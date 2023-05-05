package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PurchaseBillDAO {

    @SqlQuery(
 "   select b.id as id, b.date as date, b.user_id as user_id, b.cust_id as cust_id, b.company_id as company_id," +
       "  b.status as status, b.invoice_no as invoice_no, b.fiscal_year as fiscal_year, b.total as total, b.transportation as transportation, b.insurance" +
         " as insurance, b.loading as loading, b.other as other,  b.tax as tax," +
       "  b.discount as discount, b.grand_total as grand_total FROM purchase_bill b WHERE status = true")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getAllPurchaseBills();

    @SqlQuery(
 "   select b.id as id, b.date as date, b.user_id as user_id, b.cust_id as cust_id, b.company_id as company_id," +
       "  b.status as status, b.invoice_no as invoice_no, b.fiscal_year as fiscal_year, b.total as total, b.tax as tax," +
       "  b.discount as discount, b.grand_total as grand_total FROM purchase_bill b WHERE status = true and id = :id")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    PurchaseBillDTO getSinglePurchaseBill(Integer id);

//    @GetGeneratedKeys
//    @SqlUpdate("INSERT INTO purchase_bill (user_id, cust_id, company_id, invoice_no, fiscal_year, total, tax, discount, grand_total)" +
// " VALUES (:userId, :custId, :companyId, :invoiceNo, :fiscalYear, :total, :tax, :discount, :grandTotal)")

    @SqlUpdate("insert into purchase_bill (" +
         " fiscal_year ," +
         " purchase_bill_no ," +
         " company_id, "+
          " branch_id, "+
         " seller_id ,"+
         " seller_name ," +
         " seller_pan ," +
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
         " transaction_id  ," +
         " user_id, "+
            " sale_type, "+
            "transportation, "+
            "insurance, "+
            "loading, "+
            "other"+
         ") values (" +
         " :fiscalYear," +
         " :purchaseBillNo ," +
         " :companyId, "+
         " :branchId, "+
         " :sellerId, "+
         " :sellerName ," +
         " :sellerPan ," +
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
         " :transactionId , " +
         " :userId, "+
            " :saleType, "+
            ":transportation, "+
            ":insurance, "+
            ":loading, "+
            ":other "+
         ")")
    int addNewPurchaseBill(@BindBean PurchaseBillDTO purchaseBill);

    @SqlUpdate("UPDATE purchase_bill SET status = false WHERE id = :id")
    void deletePurchaseBill(int id);

    @SqlQuery("select  " +
         " pb.fiscal_year as  fiscal_year, " +
         " pb.company_id as company_id," +
            " pb.branch_id as branch_id, "+
         " pb.purchase_bill_no as purchase_bill_no, " +
         " pb.seller_name as seller_name, " +
         " pb.seller_pan as seller_pan, " +
         " pb.bill_date as bill_date,  " +
         " pb.amount  as amount, " +
         " pb.discount  as discount, " +
         " pb.taxable_amount  as taxable_amount, " +
         " pb.tax_amount  as tax_amount, " +
         " pb.total_amount  as total_amount, " +
         " pb.sync_with_ird as sync_with_ird, " +
         " pb.is_bill_printed  as bill_printed, " +
         " pb.is_bill_active  as bill_active, " +
         " pb.printed_time  as printed_time, " +
         " pb.entered_by  as entered_by, " +
         " pb.printed_by as printed_by, " +
         " pb.is_realtime as realtime, " +
         " pb.payment_method as payment_method, " +
         " pb.vat_refund_amount  as vat_refund_amount, " +
         " pb.transaction_id  as transaction_id ," +
         " pb.user_id as user_id,  pb.transportation as transportation, " +
            " pb.insurance as insurance, pb.loading as loading, pb.other as other," +

            "pb.sale_type as sale_type"+
         " from purchase_bill pb where pb.status = true and pb.company_id = :compId and pb.branch_id = :branchId;")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getPurchaseBillByCompanyId(@Bind int compId, @Bind int branchId);

    @SqlQuery("""
            select  
                 pb.fiscal_year as  fiscal_year, 
                 pb.purchase_bill_no as purchase_bill_no,
                 pb.seller_id as seller_id,
                 pb.seller_name as customer_name, 
                 pb.seller_pan as customer_pan, 
                 pb.bill_date as bill_date,  
                 pb.amount  as amount, 
                 pb.discount  as discount, 
                 pb.taxable_amount  as taxable_amount, 
                 pb.tax_amount  as tax_amount, 
                 pb.total_amount  as total_amount, 
                 pb.sync_with_ird as sync_with_ird, 
                 pb.is_bill_printed  as bill_printed, 
                 pb.is_bill_active  as bill_active, 
                 pb.printed_time  as printed_time, 
                 pb.entered_by  as entered_by, 
                 pb.printed_by as printed_by, 
                 pb.is_realtime as realtime, 
                 pb.payment_method as payment_method, 
                 pb.vat_refund_amount  as vat_refund_amount, 
                 pb.transaction_id  as transaction_id, 
                 pb.sale_type as sale_type
                from purchase_bill pb where pb.status = true and pb.purchase_bill_no = :billId 
                and pb.company_id = :companyId and pb.branch_id = :branchId;  
            """)
    @RegisterBeanMapper(PurchaseBillDTO.class)
    PurchaseBillDTO getBillById(int billId, int companyId, int branchId);
}
