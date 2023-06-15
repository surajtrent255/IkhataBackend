package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.PurchaseAdditionalInfoDTO;
import org.jdbi.v3.sqlobject.customizer.BindBean;

public interface PurchaseAdditionalInfoService {
    void addPurchaseAdditionalInfo( PurchaseAdditionalInfoDTO purchaseAdditionalInfoDTO);

}
