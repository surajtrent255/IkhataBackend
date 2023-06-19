package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.BillNoGenerationDTO;
import com.ishanitech.iaccountingrest.dto.BillNoGeneratorDTO;
import com.ishanitech.iaccountingrest.dto.SalesBillDTO;
import com.ishanitech.iaccountingrest.dto.SalesReceiptDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.transaction.annotation.Transactional;

public interface BillNoGeneratorDAO {

    @GetGeneratedKeys
    @SqlUpdate("""
            insert into bill_no_generator
                        (
                         fiscal_year,
                         bill_no,
                        active,
                        company_id,
                        branch_id,
                        has_abbr
                        ) values
                        (
                        :fiscalYear,
            				1,
            				true,
            				:companyId,
            				:branchId,
            				:hasAbbr   
                        )
            """)
    int createNewFiscalYear(@BindBean BillNoGenerationDTO billNoGenerationDTO);

    @SqlUpdate("update bill_no_generator set active = false where company_id = :companyId and branch_id = :branchId and fiscal_year != :fiscalYear;")
    void disableOtherYears(@Bind String fiscalYear, int companyId, int branchId);

    @Transactional
    default void initilizeBillNoForNewFiscalYear(String fiscalyear, int companyId, int branchId){
        BillNoGenerationDTO billNoGeneration = new BillNoGenerationDTO();
        billNoGeneration.setFiscalYear(fiscalyear);
        billNoGeneration.setCompanyId(companyId);
        billNoGeneration.setBranchId(branchId);
        int fiscalYearId = createNewFiscalYear(billNoGeneration);
        String fiscalYear = getCurrentFiscalYear();
        disableOtherYears(fiscalYear, companyId, branchId);
    }

    @SqlQuery("select bill_no from bill_no_generator where active = true and company_id = :companyId and branch_id = :branchId and has_abbr = :hasAbbr; ")
    int getBillNo(int companyId, int branchId, boolean hasAbbr);

    @SqlUpdate("update bill_no_generator set bill_no = bill_no +  1 where active = true and company_id = :companyId and branch_id = :branchId and has_abbr = :hasAbbr;")
    void increaseBillNo(int companyId, int branchId, boolean hasAbbr);

    @Transactional
    default int getBillNoForCurrentFiscalYear(int companyId, int branchId, boolean hasAbbr){
        int currentBillNo = getBillNo(companyId, branchId, hasAbbr);
        increaseBillNo(companyId, branchId, hasAbbr);
        return currentBillNo;
    }

    @SqlQuery("select bg.fiscal_year from bill_no_generator bg where bg.active = true LIMIT 1; ")
    String getCurrentFiscalYear();

    @SqlQuery("select bng.id as id, bng.fiscal_year as fiscal_year, " +
            " bng.bill_no as bill_no, bng.active as active " +
            " from bill_no_generator bng" +
            " where active=true;")
    @RegisterBeanMapper(BillNoGeneratorDTO.class)
    BillNoGeneratorDTO getBillNoGeneratorEntityByActive();




    @SqlQuery("select receipt_no from receipt_no_generator where active = true and company_id = :companyId and branch_id = :branchId; ")
    int getSalesReceiptNo(int companyId, int branchId);

    @SqlUpdate("update receipt_no_generator set receipt_no = receipt_no +  1 where active = true and company_id = :companyId and branch_id = :branchId;")
    void increaseReceiptNo(int companyId, int branchId);
    @Transactional
    default int getSalesReceiptNoForCurrentFiscalYear(Integer companyId, Integer branchId, boolean hasAbbr){
        int currentReceiptNo = getSalesReceiptNo(companyId, branchId);
         increaseReceiptNo(companyId, branchId);
         return currentReceiptNo;
    }

    @GetGeneratedKeys
    @SqlUpdate("""
            INSERT INTO sales_receipt(
            	 receipt_no, receipt_date, receipt_amount, bill_no, has_abbr, company_id, branch_id)
            	VALUES (:receiptNo, :receiptDate, :receiptAmount, :billNo, :hasAbbr, :companyId, :branchId);
            """)
    int createNewSalesReceipt(@BindBean SalesReceiptDTO salesBillDTO);


    @SqlQuery("SELECT * FROM sales_receipt sr where sr.bill_no = :billNo")
    @RegisterBeanMapper(SalesReceiptDTO.class)
    SalesReceiptDTO getSalesReceipt(String billNo);


    @GetGeneratedKeys
    @SqlUpdate("""
        insert into receipt_no_generator
        (
            fiscal_year,
            receipt_no,
            active,
            company_id,
            branch_id
        ) values
        (
            :fiscalYear,
            1,
            true,
            :companyId,
            :branchId
        );
            """)
   int  createNewRecieptNo(String fiscalYear, Integer companyId, Integer branchId);
}


