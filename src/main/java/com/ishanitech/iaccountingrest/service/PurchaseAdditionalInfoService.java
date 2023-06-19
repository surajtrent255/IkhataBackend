package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PurchaseAdditionalInfoDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;

import java.util.List;

public interface PurchaseAdditionalInfoService {
    void addPurchaseAdditionalInfo( PurchaseAdditionalInfoDTO purchaseAdditionalInfoDTO);

    List<PurchaseAdditionalInfoDTO> getPurchaseAdditionalAttributes( int companyId);

    void addNewAttributes( String attributeName, int companyId);


}
