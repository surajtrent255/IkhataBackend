package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.CreditNoteDAO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import com.ishanitech.iaccountingrest.service.CreditNoteService;
import com.ishanitech.iaccountingrest.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
