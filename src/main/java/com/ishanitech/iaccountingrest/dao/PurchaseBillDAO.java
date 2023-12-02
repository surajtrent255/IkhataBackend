package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.Define;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PurchaseBillDAO {

    @SqlQuery(
 "   select b.id as id, b.date as date, b.user_id as user_id, b.cust_id as cust_id, b.company_id as company_id," +
       "  b.status as status, b.invoice_no as invoice_no, b.fiscal_year as fiscal_year, b.total as total, b.transportation as transportation, b.transportation_tax_type as transportationTaxType, b.insurance" +
         " as insurance,  b.insurance_tax_type as insuranceTaxType,b.loading as loading ,b.loading_tax_type as loadingTaxType , b.other as other  , b.other_tax_type as otherTaxType ,  b.tax as tax," +
       "  b.discount as discount, b.grand_total as grand_total FROM purchase_bill b WHERE status = true")
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getAllPurchaseBills();

    @SqlQuery(
 "   select * FROM purchase_bill b WHERE status = true and purchase_bill_no = :id")
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
            " seller_address ," +
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
            "transportation_tax_type,"+
            "insurance, "+
            "insurance_tax_type, "+
            "loading, "+
            "loading_tax_type, "+
            "other,"+
            "other_tax_type,"+
            "transactional_date," +
            "transactional_date_nepali," +
            "bill_date_nepali" +

         ") values (" +
         " :fiscalYear," +
         " :purchaseBillNo ," +
         " :companyId, "+
         " :branchId, "+
         " :sellerId, "+
         " :sellerName ," +
         " :sellerPan ," +
            " :sellerAddress ," +
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
            ":transportationTaxType, "+
            ":insurance, "+
            ":insuranceTaxType, "+
            ":loading, "+
            ":loadingTaxType, "+
            ":other, "+
            ":otherTaxType, "+
            ":transactionalDate," +
            ":transactionalDateNepali," +
            ":billDateNepali" +

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
                 pb.seller_name as sellerName, 
                 pb.seller_pan as sellerPan, 
                 pb.seller_address as sellerAddress, 
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

    @SqlQuery("""
            SELECT 
                DISTINCT ON (seller_pan)
                fiscal_year AS fiscal_year,
                purchase_bill_no AS purchase_bill_no,
                seller_id AS seller_id,
                seller_name AS sellerName,
                seller_pan AS sellerPan,
                company_id AS companyId,
                branch_id AS branchId,
                seller_address AS sellerAddress,
                bill_date AS bill_date, 
                amount AS amount,
                discount AS discount,
                taxable_amount AS taxable_amount,
                tax_amount AS tax_amount,
                total_amount AS total_amount,
                sync_with_ird AS sync_with_ird,
                is_bill_printed AS bill_printed,
                is_bill_active AS bill_active,
                printed_time AS printed_time,
                entered_by AS entered_by,
                printed_by AS printed_by,
                is_realtime AS realtime,
                payment_method AS payment_method,
                vat_refund_amount AS vat_refund_amount,
                transaction_id AS transaction_id,
                sale_type AS sale_type
            FROM purchase_bill
            WHERE <caseQuery>
            """)
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getPurchaseBillBySaleTypeForCreditors( @Define String caseQuery);

    @SqlQuery("""
            select Sum(pb.total_amount) from purchase_bill pb where pb.sale_type = 2 and seller_pan = :sellerPan and pb.company_id = :companyId and pb.branch_id = :branchId;
            """)
    Double totalAmountForCreditors(String sellerPan, int companyId, int branchId);

    @SqlQuery("""
            Select * from purchase_bill where <caseQuery>
            """)
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getPurchaseBillBySellerPan(@Define String caseQuery);

    @SqlQuery("""
            select * from purchase_bill  where <caseQuery> ;
            """)
    @RegisterBeanMapper(PurchaseBillDTO.class)
    List<PurchaseBillDTO> getLimitedPurchaseBillByCompanyAndBranchId(@Define String caseQuery);

    /*
    For DashBoard Section
    return Param totalAmount of today this Month and FiscalYear
     */
    @SqlQuery("""
            select SUM(total_amount) AS totalAmount from purchase_bill where  bill_date = CAST(:todayDate AS DATE) AND company_id = :companyId AND branch_id = :branchId ;
            """)
    Double todayTotalPurchaseBillAmount(@Bind String todayDate,@Bind int companyId,@Bind int branchId);

    @SqlQuery("""
           SELECT SUM(total_amount) FROM purchase_bill WHERE <caseQuery>
            """)
    Double  monthTotalPurchaseBillAmount(@Define String caseQuery);

    @SqlQuery("""
            select SUM(total_amount) FROM purchase_bill where <caseQuery>
            """)
    Double fiscalYearTotalPurchaseBillAmount(@Define String caseQuery);

    @SqlQuery("""
            select SUM(tax_amount) AS totalTax from purchase_bill where  bill_date = CAST(:todayDate AS DATE) AND company_id = :companyId AND branch_id = :branchId ;
            """)
    Double todayTotalPurchaseBillTaxAmount(@Bind String todayDate,@Bind int companyId,@Bind int branchId);

    @SqlQuery("""
           SELECT SUM(tax_amount) AS totalTax FROM purchase_bill WHERE <caseQuery>
            """)
    Double  monthTotalPurchaseBillTaxAmount(@Define String caseQuery);

    @SqlQuery("""
            select SUM(tax_amount) AS totalTax FROM purchase_bill where <caseQuery>
            """)
    Double fiscalYearTotalPurchaseBillTaxAmount(@Define String caseQuery);

}
