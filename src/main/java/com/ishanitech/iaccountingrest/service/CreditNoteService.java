package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import org.jdbi.v3.sqlobject.customizer.BindBean;

public interface CreditNoteService {

    void addCreditNote( CreditNoteDTO creditNoteDTO);

    void addCreditNoteDetails( CreditNoteDetailsDTO creditNoteDetailsDTO);

}
