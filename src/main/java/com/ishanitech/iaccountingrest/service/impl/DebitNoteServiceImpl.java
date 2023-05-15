package com.ishanitech.iaccountingrest.service.impl;

import com.ishanitech.iaccountingrest.dao.DebitNoteDAO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import com.ishanitech.iaccountingrest.service.DbService;
import com.ishanitech.iaccountingrest.service.DebitNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
