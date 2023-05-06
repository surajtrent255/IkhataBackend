package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import org.jdbi.v3.sqlobject.customizer.BindBean;

public interface DebitNoteService {

    void addDebitNote( DebitNoteDTO debitNoteDTO);

    void addDebitNoteDetails( DebitNoteDetailsDTO debitNoteDetailsDTO);

}
