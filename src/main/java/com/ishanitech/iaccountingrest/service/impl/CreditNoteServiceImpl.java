package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CreditNoteDAO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import com.ishanitech.iaccountingrest.service.CreditNoteService;
import com.ishanitech.iaccountingrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CreditNoteServiceImpl implements CreditNoteService {
    @Autowired
    private DbService dbService;
    @Override
    public void addCreditNote(CreditNoteDTO creditNoteDTO) {
        CreditNoteDAO creditNoteDAO = dbService.getDao(CreditNoteDAO.class);
        creditNoteDAO.addCreditNote(creditNoteDTO);
    }

    @Override
    public void addCreditNoteDetails(CreditNoteDetailsDTO creditNoteDetailsDTO) {
        CreditNoteDAO creditNoteDAO = dbService.getDao(CreditNoteDAO.class);
        creditNoteDAO.addCreditNoteDetails(creditNoteDetailsDTO);

    }

    @Override
    public List<CreditNoteDTO> getCreditNoteInfo(int companyId, int branchId) {
        CreditNoteDAO creditNoteDAO = dbService.getDao(CreditNoteDAO.class);
        return creditNoteDAO.getCreditNoteInfo(companyId,branchId);
    }

    @Override
    public List<CreditNoteDetailsDTO> getCreditNoteDetailInfo(String billNumber) {
        CreditNoteDAO creditNoteDAO = dbService.getDao(CreditNoteDAO.class);
        return creditNoteDAO.getCreditNoteDetailInfo(billNumber);
    }


    @Override
    public List<CreditNoteDTO> searchCreditNoteBySearchInput(Integer offset, Integer pageTotalItems, Integer companyId, String searchInput, String searchValue) {
        String caseQuery = "";
        if (Objects.equals(searchInput,"pan") ){
            caseQuery = "company_id=" +companyId + " and pan_number = " + searchValue + " order by bill_number desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"date")){
            caseQuery = "company_id=" +companyId + " and date = CAST( '" + searchValue + "' AS DATE)" + " order by bill_number desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"billNo")){
            caseQuery = "company_id=" +companyId + " and bill_number = '" + searchValue + "' order by bill_number desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if(Objects.equals(searchInput,"customerOrSellerName")){
            caseQuery = "company_id=" +companyId + " and customer_name = '" + searchValue + "' order by bill_number desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        if (Objects.equals(searchInput,"month")){
            caseQuery = "company_id = " + companyId + " AND nepali_date LIKE '%-" + searchValue + "-%'" + " ORDER BY bill_number DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchInput,"fiscalYear")){
            caseQuery = "company_id = " + companyId + " AND fiscal_year = '" + searchValue + "'" + " ORDER BY bill_number DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if (Objects.equals(searchInput,"dateBetween")){
            String[] dateArray = searchValue.split(" ");
            String startDate = dateArray[0];
            String endDate = dateArray[1];
            caseQuery = "company_id = " + companyId + " AND date BETWEEN CAST('"+ startDate +"' AS DATE) AND CAST('"+ endDate +"' AS DATE) " + " ORDER BY bill_number DESC " +
                    "LIMIT " + pageTotalItems + " OFFSET " + (offset - 1);
        }
        if(Objects.equals(searchInput, "") || searchInput.length() == 0 ){
            caseQuery = "company_id=" +companyId + " order by bill_number desc "+
                    " limit "+ pageTotalItems+" offset "+(offset-1);
        }
        CreditNoteDAO creditNoteDAO = dbService.getDao(CreditNoteDAO.class);
        return creditNoteDAO.searchCreditNoteBySearchInput(caseQuery);
    }
}
