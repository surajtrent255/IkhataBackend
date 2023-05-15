package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CreditNoteDAO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import com.ishanitech.iaccountingrest.service.CreditNoteService;
import com.ishanitech.iaccountingrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
