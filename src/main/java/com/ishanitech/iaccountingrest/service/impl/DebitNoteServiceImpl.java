package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CreditNoteDAO;
import com.ishanitech.iaccountingrest.dao.DebitNoteDAO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.DebitNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DebitNoteServiceImpl implements DebitNoteService {

    @Autowired
    private DbService dbService;
    @Override
    public void addDebitNote(DebitNoteDTO debitNoteDTO) {
        DebitNoteDAO debitNoteDAO = dbService.getDao(DebitNoteDAO.class);
        debitNoteDAO.addDebitNote(debitNoteDTO);
    }

    @Override
    public void addDebitNoteDetails(DebitNoteDetailsDTO debitNoteDetailsDTO) {
        DebitNoteDAO debitNoteDAO = dbService.getDao(DebitNoteDAO.class);
        debitNoteDAO.addDebitNoteDetails(debitNoteDetailsDTO);
    }

    @Override
    public List<DebitNoteDTO> getDebitNoteInfo(int companyId, int branchId) {
        DebitNoteDAO debitNoteDAO = dbService.getDao(DebitNoteDAO.class);
        return debitNoteDAO.getDebitNoteInfo(companyId,branchId);
    }

    @Override
    public List<DebitNoteDetailsDTO> getDebitNoteDetailsInfo(Long billNumber) {
        DebitNoteDAO debitNoteDAO = dbService.getDao(DebitNoteDAO.class);
        return debitNoteDAO.getDebitNoteDetailsInfo(billNumber);
    }

    @Override
    public List<DebitNoteDTO> getLimitedDebitNotessByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId) {
        List<DebitNoteDTO> debitNotes;
        debitNotes = dbService.getDao(DebitNoteDAO.class).getLimitedDebitNotesByCompanyAndBranchId(offset, pageTotalItems, compId, branchId);
        return debitNotes;
    }

    @Override
    public List<DebitNoteDTO> searchDebitNoteBySearchInput(Integer offset, Integer pageTotalItems, Integer companyId, String searchInput, String searchValue) {
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
            caseQuery = "company_id=" +companyId + " and receiver_name = '" + searchValue + "' order by bill_number desc "+
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
        DebitNoteDAO debitNoteDAO = dbService.getDao(DebitNoteDAO.class);
        return debitNoteDAO.searchDebitNoteBySearchInput(caseQuery);

    }
}
