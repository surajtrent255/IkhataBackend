package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CreditNoteDTO;
import com.ishanitech.iaccountingrest.dto.CreditNoteDetailsDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;

import java.util.List;

public interface CreditNoteService {

    void addCreditNote( CreditNoteDTO creditNoteDTO);

    void addCreditNoteDetails( CreditNoteDetailsDTO creditNoteDetailsDTO);

    List<CreditNoteDTO> getCreditNoteInfo( int companyId,  int branchId);

    List<CreditNoteDetailsDTO> getCreditNoteDetailInfo( String billNumber);




}
