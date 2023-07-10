package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.DebitNoteDTO;
import com.ishanitech.iaccountingrest.dto.DebitNoteDetailsDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;

import java.util.List;

public interface DebitNoteService {

    void addDebitNote( DebitNoteDTO debitNoteDTO);

    void addDebitNoteDetails( DebitNoteDetailsDTO debitNoteDetailsDTO);

    List<DebitNoteDTO> getDebitNoteInfo( int companyId,  int branchId);

    List<DebitNoteDetailsDTO> getDebitNoteDetailsInfo( Long billNumber);


    List<DebitNoteDTO> getLimitedDebitNotessByCompIdAndBranchId(Integer offset, Integer pageTotalItems, Integer compId, Integer branchId);

    List<DebitNoteDTO> searchDebitNoteBySearchInput(Integer offset, Integer pageTotalItems,Integer companyId ,String searchInput, String searchValue);
}
