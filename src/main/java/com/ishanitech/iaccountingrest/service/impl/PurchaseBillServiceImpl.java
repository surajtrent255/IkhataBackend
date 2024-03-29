package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.PaymentDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDAO;
import com.ishanitech.iaccountingrest.dao.PurchaseBillDetailDAO;
import com.ishanitech.iaccountingrest.dto.PaginationTypeClass;
import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDTO;
import com.ishanitech.iaccountingrest.dto.PurchaseBillDetailWithProdInfo;
import com.ishanitech.iaccountingrest.dto.PurchaseReportDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.PurchaseBillService;
import com.ishanitech.iaccountingrest.utils.CustomQueryCreator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PurchaseBillServiceImpl implements PurchaseBillService {
    private final DbService dbService;
    @Override
    public List<PurchaseBillDTO> getAllPurchaseBills() {
        return dbService.getDao(PurchaseBillDAO.class).getAllPurchaseBills();
    }

    @Override
    public PurchaseBillDTO getSinglePurchasBillInfo(Integer id) {
        return dbService.getDao(PurchaseBillDAO.class).getSinglePurchaseBill(id);
    }


    @Override
    public void deletePurchaseBill(int id) {
        dbService.getDao(PurchaseBillDAO.class).deletePurchaseBill(id);
    }

    @Override
    public List<PurchaseBillDTO> getAllPurchaseBillsByCompanyId(int compId, int branchId) {
        return dbService.getDao(PurchaseBillDAO.class).getPurchaseBillByCompanyId(compId, branchId);
    }

    @Override
    public List<PurchaseBillDTO> getLimitedPurchaseBillsByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer companyId, Integer branchId,String searchInput,String searchValue) {
        List<PurchaseBillDTO> salesBillDTOList;
        String caseQuery = "";
        if (Objects.equals(searchInput,"pan") ){
            caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " and seller_pan = '" + searchValue + "' order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"date")){
            caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " and bill_date = CAST( '" + searchValue + "' AS DATE)" + " order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"billNo")){
            caseQuery = "company_id=" +companyId + " and branch_id = "+ branchId + "  and purchase_bill_no = '" + searchValue + "' order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"customerOrSellerName")){
            caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " and seller_name = '" + searchValue + "' order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if (Objects.equals(searchInput,"month")){
            caseQuery = "company_id = " + companyId + " AND branch_id = " + branchId + " AND transactional_date_nepali LIKE '%-" + searchValue + "-%'" + " ORDER BY bill_date DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchInput,"fiscalYear")){
            caseQuery = "company_id = " + companyId + " AND branch_id = " + branchId + " AND fiscal_year = '" + searchValue + "'" + " ORDER BY bill_date DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchInput,"dateBetween")){
            String[] dateArray = searchValue.split(" ");
            String startDate = dateArray[0];
            String endDate = dateArray[1];
            caseQuery = "company_id = " + companyId + " AND branch_id = " + branchId + " AND bill_date BETWEEN CAST('"+ startDate +"' AS DATE) AND CAST('"+ endDate +"' AS DATE) " + " ORDER BY bill_date DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if(Objects.equals(searchInput, " ") || searchInput.length() == 0 ){
            caseQuery = "company_id=" +companyId + " AND branch_id =" + branchId + " order by bill_date desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        salesBillDTOList = dbService.getDao(PurchaseBillDAO.class).getLimitedPurchaseBillByCompanyAndBranchId(caseQuery);
        return salesBillDTOList;
    }

    @Override
    public PurchaseReportDTO getPurchaseBillInfoForReport(Integer id, Integer compId, Integer branchId) {
        PurchaseReportDTO purchaseReportDTO = new PurchaseReportDTO();
        PurchaseBillDTO purchaseBillDTO = dbService.getDao(PurchaseBillDAO.class).getSinglePurchaseBill(id);
        List<PurchaseBillDetailWithProdInfo> purchaseBillDetailsWithProd = dbService.getDao(PurchaseBillDetailDAO.class).getPurchaseInfoWithProdNameByBillId(id, compId, branchId);
        purchaseReportDTO.setPurchaseBill(purchaseBillDTO);
        purchaseReportDTO.setPurchaseBillDetailWithProdInfos(purchaseBillDetailsWithProd);
        return purchaseReportDTO;
    }

    @Override
    public Double todayTotalPurchaseBillAmount(String todayDate, int companyId, int branchId) {
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        return purchaseBillDAO.todayTotalPurchaseBillAmount(todayDate,companyId,branchId);
    }

    @Override
    public Double ThisMonthTotalPurchaseBillAmount(String month, int companyId, int branchId) {
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        String   caseQuery  = "company_id=" +companyId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-" + month + "-%'" ;
        return purchaseBillDAO.monthTotalPurchaseBillAmount(caseQuery);
    }

    @Override
    public Double fiscalYearTotalPurchaseBillAmount(String fiscalYear, int companyId, int branchId) {
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        String caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " AND fiscal_year LIKE '%" + fiscalYear + "%'";
        return purchaseBillDAO.fiscalYearTotalPurchaseBillAmount(caseQuery);
    }

    @Override
    public Double todayTotalPurchaseBillTaxAmount(String todayDate, int companyId, int branchId) {
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        return purchaseBillDAO.todayTotalPurchaseBillTaxAmount(todayDate,companyId,branchId);
    }

    @Override
    public Double ThisMonthTotalPurchaseBillTaxAmount(String month, int companyId, int branchId) {
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        String   caseQuery  = "company_id=" +companyId + " and branch_id = " + branchId + " AND bill_date_nepali LIKE '%-" + month + "-%'" ;
        return purchaseBillDAO.monthTotalPurchaseBillTaxAmount(caseQuery);
    }

    @Override
    public Double fiscalYearTotalPurchaseBillTaxAmount(String fiscalYear, int companyId, int branchId) {
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        String caseQuery = "company_id=" +companyId + " and branch_id = " + branchId + " AND fiscal_year LIKE '%" + fiscalYear + "%'";
        return purchaseBillDAO.fiscalYearTotalPurchaseBillTaxAmount(caseQuery);

    }


    @Override
    public List<PurchaseBillDTO> getAllCreditors(HttpServletRequest request) {
        long time1 = System.currentTimeMillis();
        String caseQuery = CustomQueryCreator.generateQueryWithCase(request, PaginationTypeClass.CREDITORS, dbService);
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        PaymentDAO paymentDAO = dbService.getDao(PaymentDAO.class);
        List<PurchaseBillDTO> purchaseBillDTOs = purchaseBillDAO.getPurchaseBillBySaleTypeForCreditors(caseQuery);
        List<PaymentDTO> paymentDTOs = paymentDAO.getAllPaymentByCompanyIdAndBranchId(Integer.parseInt( request.getParameter("compId")), Integer.parseInt( request.getParameter("branchId")));

        // purchaseBillDTOs.forEach(pb->{
        //     paymentDTOs.forEach(pd->{
        //         if(pb.getSellerPan().strip().equalsIgnoreCase(pd.getPartyId().strip())){
        //             pb.setTotalAmount(pb.getTotalAmount() - pd.getAmount());
        //         }
        //     });
        // });
        // for (PurchaseBillDTO data: purchaseBillDTO){
        //     Double totalAmountPaid = Objects.requireNonNullElse(paymentDAO.getTotalPaymentByPartyIdForCreditors(data.getSellerPan(), data.getCompanyId(), data.getBranchId()), 0.0);
        //     Double totalCreditAmount = Objects.requireNonNullElse(purchaseBillDAO.totalAmountForCreditors(data.getSellerPan(), data.getCompanyId(), data.getBranchId()), 0.0);
        //     double totalAmount = totalCreditAmount - totalAmountPaid;
        //     data.setTotalAmount(totalAmount);
        // }
        long time2=System.currentTimeMillis();
        long timeDiff = time2  -time1;
        System.out.println(timeDiff + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return purchaseBillDTOs;
    }


    // public List<PurchaseBillDTO> getAllCreditors(HttpServletRequest request){
    //     String caseQuery = CustomQueryCreator.generateQueryWithCase(request, PaginationTypeClass.CREDITORS, dbService);
    //     PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        
    // }

    @Override
    public List<PurchaseBillDTO> getPurchaseBillForCreditorDetailPage(int companyId, int branchId, String sellerPan, String searchInput,Integer offset, Integer pageTotalItems) {
        String caseQuery = "" ;
        PurchaseBillDAO purchaseBillDAO = dbService.getDao(PurchaseBillDAO.class);
        if(!searchInput.isEmpty()){
            caseQuery = " company_id= " + companyId + " AND branch_id= " + branchId + " AND sale_type = 2 AND seller_pan='" + sellerPan + "' AND purchase_bill_no=" + searchInput +  " order by seller_pan desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }else{
            caseQuery = " company_id= " + companyId + " AND branch_id= " + branchId + " AND sale_type = 2 AND seller_pan= '" + sellerPan + "' order by seller_pan desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        return purchaseBillDAO.getPurchaseBillBySellerPan(caseQuery);
    }
}
