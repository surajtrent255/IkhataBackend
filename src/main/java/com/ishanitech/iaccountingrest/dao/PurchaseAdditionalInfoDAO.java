package com.ishanitech.iaccountingrest.dao;

import com.ishanitech.iaccountingrest.dto.PurchaseAdditionalInfoDTO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface PurchaseAdditionalInfoDAO {

  @SqlUpdate("""
          INSERT INTO purchase_additional_info (
              expense_id, supplier_pan, supplier_name, invoice_date, invoice_no, amount, vat_bill, company_id, branch_id, bill_no
          )
          VALUES (
              :expenseId, :supplierPan, :supplierName, :invoiceDate, :invoiceNo, :amount, :vatBill, :companyId, :branchId, :billNo
          );         
          """)
  @RegisterBeanMapper(PurchaseAdditionalInfoDTO.class)
  void addPurchaseAdditionalInfo(@BindBean PurchaseAdditionalInfoDTO purchaseAdditionalInfoDTO);


  @SqlQuery("""
          SELECT id as attributeId , attribute_name as attributeName FROM purchase_additional_attributes
          WHERE company_id = 0 OR company_id= :companyId
          """)
  @RegisterBeanMapper(PurchaseAdditionalInfoDTO.class)
  List<PurchaseAdditionalInfoDTO> getPurchaseAdditionalAttributes(@Bind int companyId);

}
