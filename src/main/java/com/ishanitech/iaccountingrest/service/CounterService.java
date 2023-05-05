package com.ishanitech.iaccountingrest.service;

import com.ishanitech.iaccountingrest.dto.CounterDTO;
import com.ishanitech.iaccountingrest.dto.UserConfigurationDTO;
import com.ishanitech.iaccountingrest.dto.UserCounterDTO;
import org.jdbi.v3.sqlobject.customizer.Bind;

import java.util.List;

public interface CounterService {
    List<CounterDTO> getAllCounterDetails(int companyId);

    Integer addCounter(CounterDTO counterDTO);

    Integer enableDisableCounter(boolean status,int id);

    List<UserConfigurationDTO> getUsersForAssignCounter(int companyId,int branchId);

    Integer assignUsersToCounter(CounterDTO counterDTO);

    List<UserCounterDTO> getUsersForCounterListing(int companyId);

    void updateUserStatusInCounter(boolean status,int userId,int counterId);

    List<CounterDTO> getUserCounterDetailsForLocalStorage( int companyId,  int userId);


}
